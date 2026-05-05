# Modelo de datos — Tags de conversación (flag ON)

> Feature flag: `feature.combee.general-tags`  
> Refs: roadmap#277, combee#827, iBB8#1498, kanban#297, kori#60, mozart-queue#190

---

## Entidades y relaciones

```mermaid
erDiagram

  OperatorTag {
    ObjectId  _id
    string    companyId
    string    tagName        "uppercase · PK de nombre"
    string    tagDescription
    string    tagColor
    Date      creationDate
    string    source         "human | chatbot | ai"
  }

  Conversation {
    ObjectId  _id
    string    companyId
    string    websiteId
    string    contactId
    string    status
  }

  GeneralTag {
    string  operatorTagId  "ref → OperatorTag._id"
    string  tagName        "uppercase · denormalizado"
    string  creatorUserId  "userId | system:chatbot | system:ai"
    Date    additionDate
    string  source         "human | chatbot | ai"
  }

  ConversationContext {
    string[]  conversation_tag_manual  "tags humanos · uppercase"
    string[]  conversation_tag         "tags chatbot/IA · lowercase"
  }

  ChatbotConfig {
    string    website_id
    string    company_id
    string[]  allConversationTags  "nombres de tags del chatbot"
  }

  Contact {
    ObjectId  _id
    string    companyId
    string[]  conversationTag  "union de ambos legacy arrays"
  }

  Conversation    ||--o{  GeneralTag         : "generalTags[]  (flag ON)"
  Conversation    ||--||  ConversationContext : "conversationContext  (legacy)"
  GeneralTag      }o--||  OperatorTag        : "ref por operatorTagId"
  ChatbotConfig   }o--o{  OperatorTag        : "sync find-or-create"
  Conversation    }o--||  Contact            : "denormaliza tags a"
```

---

## Flujos de escritura (flag ON)

```mermaid
flowchart TD
    subgraph Origenes
        H(["👤 Operador\n(kanban)"])
        CB(["🤖 Chatbot\n(iBB8)"])
        AI(["🧠 IA\n(mozart-queue)"])
    end

    subgraph combee
        addTag["POST /operatortag/addTag"]
        removeTag["PATCH /operatortag/removeTag"]
        autoTags["POST /conversations/:id/auto-tags"]
        patchConv["PATCH /conversations/:id\n(dual-write legacy)"]
        applyFn["applyAutoTagsToConversation()"]

        subgraph Conversation
            GT["generalTags[]\n{ operatorTagId, tagName,\n  creatorUserId, additionDate,\n  source }"]
            CCM["conversationContext\n.conversation_tag_manual[]"]
            CC["conversationContext\n.conversation_tag[]"]
        end

        OT[("OperatorTag\n(catálogo)")]
    end

    subgraph core
        Contact["Contact\n.conversationTag[]"]
    end

    H -->|"tagId + conversationId"| addTag
    H -->|"tagName + conversationId"| removeTag
    CB -->|"tags + source:'chatbot'"| autoTags
    CB -->|"conversation_tag (legacy)"| patchConv
    AI -->|"tags + source:'ai'"| autoTags

    addTag --> applyFn
    autoTags --> applyFn
    patchConv --> applyFn

    applyFn -->|"find-or-create"| OT
    applyFn -->|"$push · source:'human'"| GT
    applyFn -->|"$push · source:'chatbot'/'ai'"| GT
    applyFn -->|"$addToSet"| CCM
    applyFn -->|"$addToSet"| CC
    applyFn -->|"updateTagContact()"| Contact

    removeTag -->|"$pull"| GT
    removeTag -->|"$pull"| CCM
```

---

## Sync del catálogo `OperatorTag`

```mermaid
flowchart LR
    IBB["iBB8\nPUT /chatbots"]
    KORI["kori\nPATCH /integration-config"]
    V2["POST /1.0/operatortag/v2\n(find-or-create · idempotente)"]
    OT[("OperatorTag")]

    IBB -->|"flag ON\nallConversationTags"| V2
    KORI -->|"delta tags AI"| V2
    V2 --> OT
```

---

## Campos por origen

| Origen | `creatorUserId` | `source` | Escribe legacy |
|--------|----------------|----------|----------------|
| Operador humano | userId del JWT | `human` | `conversation_tag_manual` (uppercase) |
| Chatbot / iBB8 | userId del JWT → `system:chatbot` si no hay user | `chatbot` | `conversation_tag` (lowercase) |
| iBB8 campaña HSM | `system:chatbot` | `chatbot` | `conversation_tag` (lowercase) |
| IA / mozart-queue | `system:ai` | `ai` | `conversation_tag` (lowercase) |

---

## Invariantes de retrocompatibilidad

- Los campos legacy (`conversation_tag_manual`, `conversation_tag`) **siempre se escriben** — flag ON/OFF. Filtros, exports y consumers existentes no requieren migración.
- `operatorTags[]` — campo histórico en `Conversation`, ya no se escribe, se preserva.
- `Contact.conversationTag` — siempre es la unión de los dos arrays legacy.
- Ausencia de `source` en un `GeneralTag` existente → se interpreta como `human`.
