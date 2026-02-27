# AI-assisted sample-code generator for a reference CRUD app

## 1) My understanding of your goal
You want to build a system where **AI agents continuously evolve a reference CRUD application** (an “etalon” codebase).

Agents should be able to:
- Propose new functionality (e.g., add a new field type beyond String/Boolean)
- Research what’s “relevant/modern” in the ecosystem (docs, libraries, patterns)
- Turn proposals into concrete implementation work
- Coordinate with a human via a task manager (Telegram used for notifications and approvals only)
- Implement changes, add tests, run checks
- Deploy to a test environment
- Produce learning artifacts: documentation updates and a short training video
- Maintain full audit trail of decisions, prompts, diffs, and artifacts

In other words: an **agentic software factory** focused on a single evolving codebase, with human-in-the-loop governance.

---

# 2) Reference stack (v1 – fixed architectural decisions)

To reduce ambiguity for agents, we explicitly fix the initial reference stack.

## Backend
- Java + Spring Boot
- MongoDB
- OpenAPI

## Frontend
- Vue 3
- Vite
- TailwindCSS
- PrimeVue (primary component library)

⚠ PrimeVue is a strategic choice: most field types must map to existing PrimeVue components instead of creating custom UI elements.

## Repository & CI/CD
- GitLab (self-hosted or managed)
- GitLab Merge Requests as change gate
- GitLab CI pipeline
  - build
  - test
  - static analysis
  - optional security scan
  - deploy to test environment

## Testing
- JUnit
- Integration tests
- Optional e2e (future enhancement)

This stack is the **source of truth for Level A**.

---

# 3) Orchestration & Coordination

## 3.1 Orchestrator

We explicitly choose:

- n8n as the workflow orchestrator (v1 decision)

All automation flows (proposal → approval → PR → CI → deploy) are implemented in n8n.

---

## 3.2 Coordination Hub (Self-hosted)

We explicitly choose:

- Vikunja (self-hosted) as task manager
- Integration via MCP (Model Context Protocol tools)

Vikunja is the **single source of truth** for:
- Task state
- Discussions
- Status transitions
- Attached artifacts

Telegram is used strictly for:
- Notifications
- Quick approval actions
- Escalations

Telegram is NOT the system of record.

---

## 3.3 Workflow states (Level A)

Initial Kanban states (subject to change during experimentation):

- Inbox
- Researching
- Awaiting Human Input
- Approved
- In Progress
- In Review (MR opened)
- CI Failed
- Done

⚠ This state list is intentionally provisional and will evolve based on real usage.

State transitions will act as triggers inside n8n workflows.

---

# 4) Maturity levels (A → D)

| Level | Description | Autonomy | Human role |
|--------|-------------|-----------|------------|
| A | Assisted template evolution | Low | Approve most changes |
| B | Autonomous backlog + gated execution + self-improving prompts | Medium | Approve plans / risky changes |
| C | Multi-target generator | High | Strategic direction |
| D | Productized internal platform | Very high | Governance + policy |

We start with **Level A**, intentionally simplified to achieve first working results quickly.

Self-improving agents (prompt evolution, metrics-driven refinement) are introduced at **Level B**.

---

# 5) Roles & separation of duties

Logical roles:

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
4. Human gate (Vikunja + Telegram notification)
5. Branch creation in GitLab
6. Implementation
7. GitLab CI verification
8. Merge Request review
9. Test deployment
10. Artifact generation (docs + video)
11. Audit logging

## Core components

- n8n (orchestration)
- Vikunja (coordination hub, self-hosted)
- GitLab + GitLab CI
- Agent runtime (git, build, test, search, MCP tools)
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

This enables:
- Debugging agent behavior
- Reproducibility
- Governance review

---

# 8) Provider-agnostic AI layer

The system must not be tightly coupled to a single LLM provider.

Introduce adapter layer:
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

⚠ This only makes sense once a minimal working CRUD baseline exists.

## Task N0 — Create baseline CRUD implementation

Before agent-driven expansion, we must build:

- One working entity
- One working field type (e.g., String)
- End-to-end CRUD (API + UI)
- Tests passing
- GitLab CI pipeline active

Only after N0 is complete do we enable autonomous type expansion.

---

## Example field type → PrimeVue component mapping (initial 5 types)

This mapping is illustrative. A specialized agent will later expand and maintain it.

| Field Type | PrimeVue Component |
|------------|--------------------|
| String     | InputText          |
| Boolean    | InputSwitch        |
| Enum       | Dropdown           |
| Date       | Calendar           |
| Decimal    | InputNumber        |

Agents must prefer existing PrimeVue components over custom implementations.

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
- PrimeVue component mapping
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
3. Deploy self-hosted Vikunja
4. Connect Vikunja via MCP
5. Configure GitLab + GitLab CI pipeline
6. Implement minimal n8n workflow
7. Integrate AI provider via adapter layer
8. Run first controlled “field expansion” cycle

---

# 11) Level B preview — Self-improving agents

Introduced after Level A stabilization.

Concepts:
- Prompt version registry
- Postmortem analysis after CI failures
- Metrics (time-to-merge, CI failure rate)
- Controlled prompt updates (policy-gated)

---

# 12) Next discussion topics

- Risk scoring model
- Sandbox isolation strategy
- Prompt registry design
- Memory strategy (RAG over repo)
- Video generation automation approach

---

This document reflects current architectural decisions and is ready for expert review.

