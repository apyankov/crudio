# AI-assisted sample-code generator for a reference CRUD app

## 1) My understanding of your goal
You want to build a system where **AI agents continuously evolve a reference CRUD application** (an “etalon” codebase).

Agents should be able to:
- Propose new functionality (e.g., add a new field type beyond String/Boolean)
- Research what’s “relevant/modern” in the ecosystem (docs, libraries, patterns)
- Turn proposals into concrete implementation work
- **Coordinate with a human** (approval/feedback) via **Telegram**
- Implement changes, add tests, run checks
- Deploy to a test environment
- Produce learning artifacts: **documentation updates** and a **short training video**

In other words: an **agentic software factory** focused on a single evolving codebase, with human-in-the-loop governance.

---

## 2) Big-picture vision: where this can grow
Think of this as a spectrum:

### Level A — “Assisted template evolution”
- Agents only work on *well-scoped* changes: adding fields, DTOs, migrations, validations, OpenAPI updates, basic UI forms.
- Strict rules: only touch certain folders; must update tests; must pass CI.

### Level B — “Autonomous backlog + gated execution”
- Agents maintain a backlog of improvements.
- They periodically propose changes, estimate impact, write a plan.
- Human approves in Telegram, then agents implement and ship.

### Level C — “Multi-target sample-code generator”
- One reference domain model, many output targets:
  - Java/Spring (Mongo/Postgres)
  - Node/NestJS
  - Python/FastAPI
  - UI: Vue/React admin
- Agents keep templates synchronized and generate variants.

### Level D — “Productized internal platform”
- The system becomes a platform:
  - policy & compliance
  - org-wide coding standards
  - security scanning
  - SBOM, dependency updates
  - release notes

---

## 3) Conceptual architecture (high-level)
### Core loop
1. **Signal intake**: ideas from you, issues, telemetry, dependency alerts, community trends
2. **Research**: web/RAG + internal codebase understanding
3. **Proposal**: short spec + impact analysis + checklist
4. **Human gate** (Telegram): approve / request changes / reject
5. **Execution**:
   - branch creation
   - code changes
   - tests
   - docs
6. **Verification**:
   - CI pipeline
   - static analysis
   - security scanning
   - contract tests / OpenAPI checks
7. **Delivery**:
   - deploy to test
   - record video demo
   - publish docs
8. **Audit trail**: everything logged (decisions, prompts, diffs, artifacts)

### Main components
- **Orchestrator** (workflow engine): n8n / Temporal / custom
- **Agent runtime**: tools for git, build, test, web search, issue tracker, Telegram
- **Policy engine**: constraints, allowed actions, risk levels
- **Evaluation harness**: automated acceptance checks, regression suite
- **Artifact generator**: docs, changelog, tutorial video script + capture
- **Environments**: sandbox runner + test deployment

---

## 4) Key design principles
- **Safety by construction**: agents can’t push to main; only PRs; only after approvals.
- **Deterministic verification**: tests + linters + static analysis are the “truth.”
- **Small diffs**: prefer incremental PRs.
- **Reproducibility**: pinned toolchain, dockerized builds.
- **Separation of duties**:
  - Research agent ≠ Code agent ≠ Reviewer agent
- **Observability**: logs, metrics, PR annotations, traceability from idea → commit.

---

## 5) Immediate next steps (a sensible starting scope)
**Start with the “field/type expansion” use-case** because it’s concrete:
- Adding a new field type (e.g., Decimal, Money, Enum, Date/Time, JSON, Geo)
- Touchpoints are predictable:
  - entity/model
  - DTO + validation
  - persistence mapping
  - API schema
  - UI form + display
  - tests
  - docs

Deliver the first working loop:
- Agent proposes → human approves in Telegram → agent opens PR → CI passes → auto-deploy test.

---

## 6) Questions to choose direction
1. **Primary goal**: is it mainly a *learning/research system* or a *production-grade internal platform*?
2. **Your reference CRUD stack** right now: Java/Spring + Mongo (as you mentioned), plus UI (Vue/React)?
3. Do you want the agent to be able to **change architecture** (new modules) or only do **bounded changes**?
4. What is the required **human approval policy**?
   - approve every change
   - approve only “risky” changes
   - approve only releases
5. Which artifacts matter most: tests, docs, video, or all equally?

---

## 7) Next section to expand (pick one)
A) A concrete **MVP workflow** (steps, states, failure handling)
B) The **tooling layer** (git/CI, test harness, sandbox runner, Telegram integration)
C) The **policy & guardrails** (permissions, risk scoring, escalation)
D) The **domain model / type system** for “new field types” and how to implement it cleanly

