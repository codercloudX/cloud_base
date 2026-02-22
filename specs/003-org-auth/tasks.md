# Tasks: 组织架构与权限体系维护系统

**Input**: Design documents from `/specs/003-org-auth/`
**Prerequisites**: plan.md, spec.md, research.md, data-model.md

**Tests**: Tests are included for all user stories to follow Test-Driven Development principles

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

## Path Conventions

- **Frontend**: `front-end/src/`
- **Backend**: `back-end/src/main/java/com/cloudbase/`
- **Tests**: `front-end/tests/` and `back-end/src/test/java/com/cloudbase/`

## Phase 1: Setup (Project Initialization)

**Purpose**: Project initialization and basic structure setup

- [x] T001 [P] Initialize frontend project with Vue 3 + TypeScript + Vite in `front-end/`
- [x] T002 [P] Initialize backend project with Spring Boot 3.0+ in `back-end/`
- [x] T003 [P] Configure frontend dependencies (Element Plus, Pinia, Axios, SCSS) in `front-end/package.json`
- [x] T004 [P] Configure backend dependencies (MyBatis-Plus, Spring Security) in `back-end/pom.xml`
- [x] T005 Configure database connection and create MySQL database
- [x] T006 [P] Configure frontend routing structure in `front-end/src/router/index.ts`
- [x] T007 [P] Configure backend API base path in `back-end/src/main/java/com/cloudbase/config/WebConfig.java`

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure that MUST be complete before ANY user story can be implemented

**⚠️ CRITICAL**: No user story work can begin until this phase is complete

- [x] T008 [P] Create frontend type definitions in `front-end/src/types/`
- [x] T009 [P] Create backend entities in `back-end/src/main/java/com/cloudbase/entity/`
- [x] T010 [P] Implement frontend API request封装 in `front-end/src/api/index.ts`
- [x] T011 [P] Implement backend data access layer in `back-end/src/main/java/com/cloudbase/repository/`
- [x] T012 [P] Create database table structure with `auth_` prefix
- [x] T013 Configure Spring Security authentication/authorization in `back-end/src/main/java/com/cloudbase/config/SecurityConfig.java`
- [x] T014 Create system management component in `front-end/src/views/system/SystemManagement.vue`
- [x] T015 Define permission codes and structure in `back-end/src/main/java/com/cloudbase/constants/PermissionConstants.java`

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

## Phase 3: User Story 1 - 用户管理 (Priority: P1) 🎯 MVP

**Goal**: Enable management of system users including creation, editing, password reset, and account注销

**Independent Test**: Create test users, edit user information, reset passwords, and注销 users

### Tests for User Story 1 ⚠️

> **NOTE: Write these tests FIRST, ensure they FAIL before implementation**

- [x] T016 [P] [US1] Frontend unit tests for user management in `front-end/tests/unit/user.spec.ts`
- [x] T017 [P] [US1] Backend unit tests for user service in `back-end/src/test/java/com/cloudbase/service/UserServiceTest.java`
- [x] T018 [P] [US1] Integration tests for user API endpoints in `back-end/src/test/java/com/cloudbase/controller/UserControllerTest.java`

### Implementation for User Story 1

- [x] T019 [P] [US1] Create frontend user management component in `front-end/src/views/user/UserManagement.vue`
- [x] T020 [P] [US1] Implement backend user controller in `back-end/src/main/java/com/cloudbase/controller/UserController.java`
- [x] T021 [P] [US1] Implement frontend user API calls in `front-end/src/api/user.ts`
- [x] T022 [P] [US1] Implement backend user service in `back-end/src/main/java/com/cloudbase/service/impl/UserServiceImpl.java`
- [x] T023 [US1] Implement frontend user list with pagination in `front-end/src/views/user/UserManagement.vue`
- [x] T024 [US1] Implement backend user list query endpoint in `back-end/src/main/java/com/cloudbase/controller/UserController.java`
- [x] T025 [US1] Implement frontend user creation form in `front-end/src/views/user/UserManagement.vue`
- [x] T026 [US1] Implement backend user creation endpoint in `back-end/src/main/java/com/cloudbase/controller/UserController.java`
- [x] T027 [US1] Implement frontend user editing functionality in `front-end/src/views/user/UserManagement.vue`
- [x] T028 [US1] Implement backend user update endpoint in `back-end/src/main/java/com/cloudbase/controller/UserController.java`
- [x] T029 [US1] Implement frontend password reset functionality in `front-end/src/views/user/UserManagement.vue`
- [x] T030 [US1] Implement backend password reset endpoint in `back-end/src/main/java/com/cloudbase/controller/UserController.java`
- [x] T031 [US1] Implement frontend user注销 functionality in `front-end/src/views/user/UserManagement.vue`
- [x] T032 [US1] Implement backend user注销 endpoint in `back-end/src/main/java/com/cloudbase/controller/UserController.java`
- [x] T033 [US1] Implement frontend batch operations in `front-end/src/views/user/UserManagement.vue`
- [x] T034 [US1] Implement backend batch operations endpoint in `back-end/src/main/java/com/cloudbase/controller/UserController.java`

**Checkpoint**: At this point, User Story 1 should be fully functional and testable independently

## Phase 4: User Story 2 - 部门管理 (Priority: P1)

**Goal**: Enable management of department structure including creation, merging, renaming, and撤销

**Independent Test**: Create test departments, merge departments, rename departments,撤销 departments, and adjust department hierarchy

### Tests for User Story 2 ⚠️

> **NOTE: Write these tests FIRST, ensure they FAIL before implementation**

- [ ] T035 [P] [US2] Frontend unit tests for department management in `front-end/tests/unit/department.spec.ts`
- [ ] T036 [P] [US2] Backend unit tests for department service in `back-end/src/test/java/com/cloudbase/service/DepartmentServiceTest.java`
- [ ] T037 [P] [US2] Integration tests for department API endpoints in `back-end/src/test/java/com/cloudbase/controller/DepartmentControllerTest.java`

### Implementation for User Story 2

- [ ] T038 [P] [US2] Create frontend department management component in `front-end/src/views/department/DepartmentManagement.vue`
- [ ] T039 [P] [US2] Implement backend department controller in `back-end/src/main/java/com/cloudbase/controller/DepartmentController.java`
- [ ] T040 [P] [US2] Implement frontend department API calls in `front-end/src/api/department.ts`
- [ ] T041 [P] [US2] Implement backend department service in `back-end/src/main/java/com/cloudbase/service/impl/DepartmentServiceImpl.java`
- [ ] T042 [US2] Implement frontend department tree structure in `front-end/src/views/department/DepartmentManagement.vue`
- [ ] T043 [US2] Implement backend department tree query endpoint in `back-end/src/main/java/com/cloudbase/controller/DepartmentController.java`
- [ ] T044 [US2] Implement frontend department creation form in `front-end/src/views/department/DepartmentManagement.vue`
- [ ] T045 [US2] Implement backend department creation endpoint in `back-end/src/main/java/com/cloudbase/controller/DepartmentController.java`
- [ ] T046 [US2] Implement frontend department editing functionality in `front-end/src/views/department/DepartmentManagement.vue`
- [ ] T047 [US2] Implement backend department update endpoint in `back-end/src/main/java/com/cloudbase/controller/DepartmentController.java`
- [ ] T048 [US2] Implement frontend department merging functionality in `front-end/src/views/department/DepartmentManagement.vue`
- [ ] T049 [US2] Implement backend department merging endpoint in `back-end/src/main/java/com/cloudbase/controller/DepartmentController.java`
- [ ] T050 [US2] Implement frontend department撤销 functionality in `front-end/src/views/department/DepartmentManagement.vue`
- [ ] T051 [US2] Implement backend department撤销 endpoint in `back-end/src/main/java/com/cloudbase/controller/DepartmentController.java`
- [ ] T052 [US2] Implement frontend department hierarchy adjustment in `front-end/src/views/department/DepartmentManagement.vue`
- [ ] T053 [US2] Implement backend department hierarchy update endpoint in `back-end/src/main/java/com/cloudbase/controller/DepartmentController.java`

**Checkpoint**: At this point, User Story 2 should be fully functional and testable independently

## Phase 5: User Story 3 - 角色管理 (Priority: P1)

**Goal**: Enable management of roles including definition, permission setting, and user role assignment

**Independent Test**: Create test roles, modify role permissions, assign roles to users, and verify permission enforcement

### Tests for User Story 3 ⚠️

> **NOTE: Write these tests FIRST, ensure they FAIL before implementation**

- [ ] T054 [P] [US3] Frontend unit tests for role management in `front-end/tests/unit/role.spec.ts`
- [ ] T055 [P] [US3] Backend unit tests for role service in `back-end/src/test/java/com/cloudbase/service/RoleServiceTest.java`
- [ ] T056 [P] [US3] Integration tests for role API endpoints in `back-end/src/test/java/com/cloudbase/controller/RoleControllerTest.java`
- [ ] T057 [P] [US3] Permission validation tests in `back-end/src/test/java/com/cloudbase/security/PermissionTest.java`

### Implementation for User Story 3

- [ ] T058 [P] [US3] Create frontend role management component in `front-end/src/views/role/RoleManagement.vue`
- [ ] T059 [P] [US3] Implement backend role controller in `back-end/src/main/java/com/cloudbase/controller/RoleController.java`
- [ ] T060 [P] [US3] Implement frontend role API calls in `front-end/src/api/role.ts`
- [ ] T061 [P] [US3] Implement backend role service in `back-end/src/main/java/com/cloudbase/service/impl/RoleServiceImpl.java`
- [ ] T062 [P] [US3] Implement backend permission controller in `back-end/src/main/java/com/cloudbase/controller/PermissionController.java`
- [ ] T063 [P] [US3] Implement frontend permission API calls in `front-end/src/api/permission.ts`
- [ ] T064 [US3] Implement frontend role list in `front-end/src/views/role/RoleManagement.vue`
- [ ] T065 [US3] Implement backend role list query endpoint in `back-end/src/main/java/com/cloudbase/controller/RoleController.java`
- [ ] T066 [US3] Implement frontend role creation form in `front-end/src/views/role/RoleManagement.vue`
- [ ] T067 [US3] Implement backend role creation endpoint in `back-end/src/main/java/com/cloudbase/controller/RoleController.java`
- [ ] T068 [US3] Implement frontend role permission setting in `front-end/src/views/role/RoleManagement.vue`
- [ ] T069 [US3] Implement backend role permission update endpoint in `back-end/src/main/java/com/cloudbase/controller/RoleController.java`
- [ ] T070 [US3] Implement frontend user role assignment in `front-end/src/views/user/UserManagement.vue`
- [ ] T071 [US3] Implement backend user role assignment endpoint in `back-end/src/main/java/com/cloudbase/controller/UserController.java`
- [ ] T072 [US3] Implement frontend permission validation in `front-end/src/utils/permission.ts`
- [ ] T073 [US3] Implement backend permission validation in `back-end/src/main/java/com/cloudbase/security/PermissionValidator.java`

**Checkpoint**: At this point, User Story 3 should be fully functional and testable independently

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Improvements that affect multiple user stories

- [ ] T074 [P] Create system administrator initial account in `back-end/src/main/java/com/cloudbase/service/impl/InitServiceImpl.java`
- [ ] T075 [P] Implement frontend responsive layout improvements
- [ ] T076 [P] Optimize backend performance with caching in `back-end/src/main/java/com/cloudbase/config/CacheConfig.java`
- [ ] T077 [P] Add comprehensive logging in `back-end/src/main/java/com/cloudbase/config/LoggingConfig.java`
- [ ] T078 [P] Conduct security hardening and vulnerability scanning
- [ ] T079 Run integration tests to verify all user stories work together
- [ ] T080 Run performance tests to ensure response times under 1 second
- [ ] T081 Verify test coverage达到 80% for all production code
- [ ] T082 Create deployment scripts for test environment
- [ ] T083 Document system usage and technical details

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - BLOCKS all user stories
- **User Stories (Phase 3-5)**: All depend on Foundational phase completion
  - User stories can then proceed in parallel (if staffed)
  - Or sequentially in priority order (P1 → P2 → P3)
- **Polish (Phase 6)**: Depends on all user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories
- **User Story 2 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories
- **User Story 3 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories

### Within Each User Story

- Tests MUST be written and FAIL before implementing
- Models before services
- Services before endpoints
- Core implementation before integration
- Story complete before moving to next priority

### Parallel Opportunities

- All Setup tasks marked [P] can run in parallel
- All Foundational tasks marked [P] can run in parallel (within Phase 2)
- Once Foundational phase completes, all user stories can start in parallel (if team capacity allows)
- All tests for a user story marked [P] can run in parallel
- Models and controllers within a story marked [P] can run in parallel
- Different user stories can be worked on in parallel by different team members

## Parallel Example: User Story 1

```bash
# Launch all tests for User Story 1 together:
Task: "Frontend unit tests for user management in front-end/tests/unit/user.spec.ts"
Task: "Backend unit tests for user service in back-end/src/test/java/com/cloudbase/service/UserServiceTest.java"
Task: "Integration tests for user API endpoints in back-end/src/test/java/com/cloudbase/controller/UserControllerTest.java"

# Launch all models and controllers for User Story 1 together:
Task: "Create frontend user management component in front-end/src/views/user/UserManagement.vue"
Task: "Implement backend user controller in back-end/src/main/java/com/cloudbase/controller/UserController.java"
Task: "Implement frontend user API calls in front-end/src/api/user.ts"
Task: "Implement backend user service in back-end/src/main/java/com/cloudbase/service/impl/UserServiceImpl.java"
```

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational (CRITICAL - blocks all stories)
3. Complete Phase 3: User Story 1
4. **STOP and VALIDATE**: Test User Story 1 independently
5. Deploy/demo if ready

### Incremental Delivery

1. Complete Setup + Foundational → Foundation ready
2. Add User Story 1 → Test independently → Deploy/Demo (MVP!)
3. Add User Story 2 → Test independently → Deploy/Demo
4. Add User Story 3 → Test independently → Deploy/Demo
5. Each story adds value without breaking previous stories

### Parallel Team Strategy

With multiple developers:

1. Team completes Setup + Foundational together
2. Once Foundational is done:
   - Developer A: User Story 1 (user management)
   - Developer B: User Story 2 (department management)
   - Developer C: User Story 3 (role management)
3. Stories complete and integrate independently

## Notes

- [P] tasks = different files, no dependencies
- [Story] label maps task to specific user story for traceability
- Each user story should be independently completable and testable
- Verify tests fail before implementing
- Commit after each task or logical group
- Stop at any checkpoint to validate story independently
- Avoid: vague tasks, same file conflicts, cross-story dependencies that break independence

## Implementation Notes

- **Database Table Prefix**: Using `auth_` prefix for all tables
- **Test Coverage**: Targeting 80%+ coverage for all production code
- **Security**: Implementing Spring Security with RBAC model
- **Performance**: Implementing caching for frequently accessed data
- **Scalability**: Designing for 1000+ users and 100+ departments

---

**Generated**: 2026-02-16
**Total Tasks**: 83
**Parallel Tasks**: 26
**Test Tasks**: 14