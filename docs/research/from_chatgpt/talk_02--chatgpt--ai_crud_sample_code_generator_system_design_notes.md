# AI-assisted sample-code generator for a reference CRUD app

## 1) My understanding of your goal
You want to build a system where **AI agents continuously evolve a reference CRUD application** (an “etalon” codebase).

Agents should be able to:
- Propose new functionality (e.g., add a new field type beyond String/Boolean)
- Research what’s “relevant/modern” in the ecosystem (docs, libraries, patterns)
- Turn proposals into concrete implementation work
- **Coordinate with a human** (approval/feedback) via Telegram
- Implement changes, add tests, run checks
- Deploy to a test environment
- Produce learning artifacts: documentation updates and a short training video
- Maintain full audit trail of decisions, prompts, diffs, and artifacts

In other words: an **agentic software factory** focused on a single evolving codebase, with human-in-the-loop governance.

---

# 2) Reference stack (v1 – baseline truth)

To reduce ambiguity for agents, we explicitly fix the initial reference stack.

Backend:
- Java + Spring Boot
- MongoDB (initial persistence)
- OpenAPI (contract-first or generated, to be defined)

Frontend:
- Vue 3 (admin UI)

Testing:
- JUnit + integration tests
- Basic e2e (optional in v1, but desirable)

CI/CD:
- Git-based workflow (feature branches + PR)
- CI pipeline with build + test + static analysis
- Auto-deploy to test environment

This stack is the **source of truth for Level A**. Later levels may expand to multi-target generation.

---

# 3) Maturity levels (A → D)

| Level | Description | Autonomy | Human role |
|--------|-------------|-----------|------------|
| A | Assisted template evolution | Low | Approve most changes |
| B | Autonomous backlog + gated execution | Medium | Approve plans / risky changes |
| C | Multi-target generator | High | Strategic direction |
| D | Productized internal platform | Very high | Governance + policy |

We start with **Level A**.

---

# 4) Coordination model

## 4.1 Task Manager = Source of Truth

We introduce a **coordination hub (task manager)** as the single source of truth for state.

Telegram is used for:
- Notifications
- Quick approvals
- Escalations

But:
- Task state
- Discussions
- Artifacts
- Status transitions

are persisted in the task system.

This prevents Telegram from becoming the system-of-record.

## 4.2 Workflow states (Level A)

Initial Kanban states (subject to change as we evolve the system):

- Inbox
- Researching
- Awaiting Human Input
- Approved
- In Progress
- In Review (PR opened)
- CI Failed
- Done

⚠ Note: This state list is intentionally provisional and will evolve during real-world experimentation.

State transitions will later become formal triggers in the orchestrator (n8n or equivalent).

---

# 5) Roles & separation of duties

To avoid “one super-agent that does everything”, we define logical roles:

- Research Agent
  - Explores web/docs
  - Evaluates libraries/patterns
  - Writes proposal

- Dev Agent
  - Modifies code
  - Writes tests
  - Updates OpenAPI
  - Adjusts UI

- Review Agent
  - Checks diff coherence
  - Validates architectural boundaries
  - Runs policy checks

- Content Agent
  - Updates documentation
  - Prepares tutorial script
  - Generates release notes

- Policy Engine (mandatory layer)
  - Enforces folder boundaries
  - Defines risk levels
  - Controls permissions
  - Blocks unsafe operations

---

# 6) Architecture (high-level)

## Core loop

1. Signal intake (idea, telemetry, dependency alert)
2. Research phase
3. Proposal (impact + checklist)
4. Human gate (Telegram + task manager)
5. Branch creation
6. Implementation
7. CI verification
8. PR review
9. Test deployment
10. Artifact generation (docs + video)
11. Audit logging

## Core components

- Orchestrator (n8n / Temporal / custom)
- Agent runtime (git, build, test, search, Telegram, task API)
- Policy engine
- Evaluation harness (CI, regression tests)
- Artifact generator
- Sandbox runner (isolated execution)

---

# 7) Audit Trail & Observability

The system must log:

- Prompts used by agents
- Retrieved research sources
- Generated plans
- Diffs applied
- CI outputs
- Human decisions
- Final artifacts

This is critical for:
- Debugging agent behavior
- Reproducibility
- Governance review

Sensitive data policies must be defined before enabling production-level logging.

---

# 8) Provider-agnostic AI layer

The system must not be tightly coupled to a single LLM provider.

Introduce an abstraction layer:
- Research model adapter
- Code model adapter
- Review model adapter

This allows:
- Switching providers
- Mixing local + hosted models
- Cost optimization

---

# 9) Field/Type Expansion Strategy (Level A focus)

Field/type expansion is our primary structured change scenario.

However:

⚠ This only makes sense once a minimal working CRUD baseline exists.

Therefore we define:

## Task N0 — Create baseline CRUD implementation

Before agent-driven expansion, we must build:

- One working entity
- One working field type (e.g., String)
- End-to-end CRUD (API + UI)
- Tests passing
- CI pipeline active

Only after N0 is complete do we enable autonomous type expansion.

---

## Expansion change checklist (backend + frontend)

When introducing a new field/type, the agent must verify updates to:

Backend:
- Entity/model
- DTO
- Validation rules
- Persistence mapping
- OpenAPI schema
- Unit tests
- Integration tests

Frontend:
- Form input component
- Validation UI
- Table display
- Filtering/sorting (if relevant)

Docs:
- README update
- Example payloads
- Migration notes (if needed)

PR must fail if any required section is untouched.

---

# 10) Immediate execution plan (Level A)

1. Build N0 baseline CRUD app
2. Define repository structure + folder boundaries
3. Introduce task manager (source of truth)
4. Connect Telegram for notifications + approvals
5. Implement minimal orchestrator workflow
6. Integrate AI provider via adapter layer
7. Run first controlled “field expansion” cycle

---

# 11) Next discussion topics

- Risk scoring model (what is “risky change”?)
- Sandbox isolation strategy
- Prompt versioning strategy
- Memory strategy (RAG over repo?)
- Video generation automation approach

---

This document is now ready for expert review and iteration.

