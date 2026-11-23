---
name: feature-com-spec
description: Reusable Spec-Driven Development workflow for scoped software features, from issue intake through implementation, validation, and pull request.
---

# Spec-Driven Feature Workflow

Use this workflow for a feature in any repository. Follow the repository's
contribution guidance and obtain approval at each decision gate before
proceeding.

## Mandatory rules

- Never implement code before the user explicitly approves the specification.
- Never execute a later plan task without explicit authorization for that task.
- Keep each commit limited to one approved plan task; never mix task commits.
- Do not expand scope, add dependencies, or modify unrelated files without
  explicit approval.
- Preserve existing user changes and untracked files. Inspect Git status before
  work and stage only explicitly reviewed paths.
- If code, repository guidance, the specification, or the plan conflict, stop,
  explain the evidence, and ask how to proceed.
- Mark a task complete only after its required validation succeeds.
- Do not merge or push without explicit authorization.

## Workflow

1. **Issue and branch**
   - Confirm the feature request and its issue or tracking reference.
   - Create or use a feature branch associated with that work.
   - Record the branch and issue reference; do not invent an issue number.
   - Inspect the worktree and preserve existing changes.
2. **Repository guidance**
   - Read `AGENTS.md` and the relevant project documentation and source files.
   - Identify the current behavior, conventions, tests, and scope boundaries.
   - If repository guidance is missing, ask before creating it unless its
     creation is explicitly requested.
3. **Clarify and write the specification**
   - Ask focused questions about unresolved business rules and edge cases.
   - Write the specification before changing implementation code.
   - Include purpose, scope and non-scope, functional behavior, edge cases, and
     measurable acceptance criteria.
   - Present the complete specification and wait for explicit approval.
4. **Create and approve the plan**
   - Break implementation into small, independently reviewable tasks.
   - For each task, name the files to change, expected result, validation, and
     actual dependencies.
   - Order tasks by dependency and identify the final regression step.
   - Present the plan and wait for explicit approval before implementation.
5. **Implement one task at a time**
   - Read the complete authorized task, then recheck Git status and inspect its
     target files.
   - Implement only that task. Do not begin later tasks.
   - If implementation reveals a specification gap or conflict, stop and
     propose a specification update. Obtain approval for the revised spec and
     plan before continuing.
6. **Validate and review each task**
   - Run the task's planned tests or checks; add no tooling unless necessary and
     approved.
   - Review the full task diff for scope, regressions, and whitespace errors.
   - Update the plan only after implementation and validation succeed.
   - Present changed files, validation results, and the diff for review.
7. **Commit by task**
   - After approval, stage only reviewed files belonging to the current task.
   - Verify the staged diff and commit with a task-specific message.
   - Confirm the commit hash and worktree status. Do not include later tasks or
     unrelated files.
8. **Final validation and pull request**
   - After all tasks are approved and complete, run the final regression and
     verify all acceptance criteria, documentation, and commit order.
   - Summarize known limitations and validation results.
   - Prepare a pull request that links the issue and summarizes the changes and
     tests. Request review; do not merge or push without authorization.

## Specification template

```markdown
# Feature specification: <feature name>

## Problem and goal
<Who needs what and why?>

## Scope
- In scope: <behaviors included>
- Out of scope: <explicit exclusions>

## Functional requirements
1. <Observable behavior>
2. <Observable behavior>

## Business rules and edge cases
- <Input and boundary behavior>
- <Empty/error states>

## Acceptance criteria
- [ ] <Measurable condition>
- [ ] <Measurable condition>

## Open questions
- <Unresolved decisions, or "None">
```

## Plan template

```markdown
# Implementation plan: <feature name>

## Tasks

1. [ ] <Small task title>
   - Files: `<path>`, `<path>`
   - Change: <bounded implementation result>
   - Validation: <test or concrete verification>
   - Depends on: <task number(s), or none>

2. [ ] <Small task title>
   - Files: `<path>`
   - Change: <bounded implementation result>
   - Validation: <test or concrete verification>
   - Depends on: <task number(s), or none>
```
