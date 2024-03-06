package com.example.todobackver2.autoseed;

import com.example.todobackver2.Utils.GenerateId;
import com.example.todobackver2.entity.*;
import com.example.todobackver2.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
public class autoseed implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    Project_userRepository project_userRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    Workspace_userRepository workspace_userRepository;
    @Autowired
    RoomUserRepository roomUserRepository;
    @Autowired
    RoomChatRepository roomChatRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.size() == 0) {
            for (Long i = 0L; i < 10L; i++) {
                UserEntity user = new UserEntity();
                user.setPassword(bCryptPasswordEncoder.encode("12345678"));
                user.setUserName(GenerateId.generateRandomString(10));
                user.setEmail(GenerateId.generateRandomString(10) + "@gmail.com");
                UserEntity savedUser = userRepository.save(user);
                userEntityList.add(savedUser);
            }

            List<Workspace> workspaces = new ArrayList<>();
            Map<Long, List<Long>> workspaceUserMap = new HashMap<>();
            for (Long i = 0L; i < 20L; i++) {
                Workspace workspace = new Workspace();
                Workspace_user workspace_user = new Workspace_user();
                workspace.setWorkspaceName(GenerateId.generateRandomString(10));
                UserEntity user = userEntityList.get(random.nextInt(10));
                workspace.setUser(user);
                workspace.setId(i);
                workspace_user.setUser(user);
                workspace_user.setUserId(user.getId());
                Workspace savedWorkspace = workspaceRepository.save(workspace);
                workspaces.add(savedWorkspace);
                workspace_user.setWorkspaceId(savedWorkspace.getId());
                workspace_user.setWorkspace(savedWorkspace);
                Workspace_user savedWorkspaceUser = workspace_userRepository.save(workspace_user);
                List<Long> userIds = new ArrayList<>();
                userIds.add(user.getId());
                workspaceUserMap.put(savedWorkspace.getId(), userIds);
            }

            for (Long i = 0L; i < 20L; i++) {
                Workspace_user workspace_user = new Workspace_user();
                Workspace workspace = workspaces.get(random.nextInt(20));
                List<Long> userIds = workspaceUserMap.get(workspace.getId());
                UserEntity user;
                do {
                    user = userEntityList.get(random.nextInt(10));
                } while (userIds.contains(user.getId()));
                userIds.add(user.getId());
                workspace_user.setUser(user);
                workspace_user.setUserId(user.getId());
                workspace_user.setWorkspace(workspace);
                workspace_user.setWorkspaceId(workspace.getId());
                workspace_userRepository.save(workspace_user);
                workspaceUserMap.put(workspace.getId(), userIds);
            }
            List<ProjectEntity> projectEntities = new ArrayList<>();
            for (Long i = 0L; i < 20L; i++) {
                ProjectEntity projectEntity = new ProjectEntity();
                projectEntity.setProjectName(GenerateId.generateRandomString(20));
                projectEntity.setDayEnd(new Date());
                projectEntity.setDayBegin(new Date());
                projectEntity.setDescription(GenerateId.generateRandomString(5));
                projectEntity.setCreatedAt(new Date());
                projectEntity.setUpdatedAt(new Date());
                projectEntity.setId(i);
                projectEntity.setWorkspace(workspaces.get(random.nextInt(20)));
                ProjectEntity savedProject= projectRepository.save(projectEntity);
                projectEntities.add(savedProject);
            }
            List<Project_user> project_users=new ArrayList<>();
            for (Long i = 0L; i < 40L; i++) {
                Project_user project_user = new Project_user();
                Workspace workspace = workspaces.get(random.nextInt(20));
                UserEntity userFilter = userRepository.findById(workspaceUserMap.get(workspace.getId()).get(random.nextInt(workspaceUserMap.get(workspace.getId()).size()))).get();
                project_user.setUserId(userFilter.getId());
                project_user.setUser(userFilter);
                ProjectEntity projectEntity = projectEntities.get(random.nextInt(20));
                project_user.setProject(projectEntity);
                project_user.setProjectId(projectEntity.getId());
                Project_user saveProjectUser= project_userRepository.save(project_user);
                project_users.add(saveProjectUser);

            }

            for(Long i = 0L; i < 40L; i++){
                TaskEntity task=new TaskEntity();
                task.setTaskName(GenerateId.generateRandomString(10));
                task.setDone(random.nextBoolean());
                task.setPriority(random.nextInt(3)+1);
                task.setDayEnd(new Date());
                ProjectEntity project=projectRepository.findById(project_users.get(random.nextInt(40)).getProjectId()).get();
                List<Project_user> project_users2=project_userRepository.findAllByProjectId(project.getId());
                UserEntity user=userRepository.findById(project_users2.get(random.nextInt(project_users2.size())).getUserId()).get();
                task.setUserEntity(user);
                task.setProject(project);
                task.setCreatedAt(new Date());
                task.setUpdatedAt(new Date());
                task.setGhim(random.nextBoolean());
                task.setDayBegin(new Date());
                taskRepository.save(task);
            }


        }

    }

    private Date generateRandomDate() {
        long minDay = Timestamp.valueOf("2000-01-01 00:00:00").getTime();
        long maxDay = Timestamp.valueOf("2023-12-31 00:00:00").getTime();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return new Date(randomDay);
    }

}
