package com.example.test.gitplugin.Utils;

import com.example.test.gitplugin.ScriptExecutor;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.TreeFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JgitTes {

    private static List<String> commands;

    public static void main(String[] args) {
        try {
//            jgitClone();
//            jgitPull();
//            jgitPush();
//            jgitAdd();
//            getBranchRefs();
//            getBranchReferenceFromRepo();
//            getCommitHash();
//            executeScript();
//            deleteIfExist();
//            cloneViaProcessBuilder();
//            gitPullViaCommand();
//            getCommitHashHead(new File("C:\\Work\\Development\\Git_Hub_Local_Repos\\repository_export\\PayPal"));
            getDiff();
//                setRemoteOrigin();
//            commitViaProcessBuilder();
            pushViaProcessBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void gitPullViaCommand() throws IOException {
        List<String> commands = Arrays.asList("git", "pull", "-v");
        File dir = new File("C:\\Work\\Bitbucket\\gcc-plugin-gcs");
        Process process = new ProcessBuilder().command(commands).directory(dir).redirectErrorStream(true).start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void deleteIfExist() throws IOException {
        String branch = "PayPal";
        String path = "C:\\Work\\Development\\Test Repo\\" + branch;

        System.out.println(Thread.currentThread().getName());

//        Files.deleteIfExists(Paths.get(path));
        org.apache.commons.io.FileUtils.deleteQuietly(new File(path));
    }

    public static void getBranchRefs() {
        Collection<Ref> refs = null;
        try {
            String gitHubUrl = "https://oauth2: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@github.com/tpt-gcc-qa-github/Conflicts.git";
            String userName = "SA-ITS-translation";
            String password = "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";
            String cloneDir = "C:\\Work\\Development\\Test Repo\\jgit\\";
            String branch = "PS-TEST";
            refs = Git.lsRemoteRepository().setHeads(true).setTags(true).setRemote(gitHubUrl)
                    .call();
            System.out.println("refs: " + refs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getBranchReferenceFromRepo() throws IOException {
        String path = "C:\\Work\\Development\\Git_Hub_Local_Repos\\repository_export\\";
        String branch = "PayPal";
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder
                .setWorkTree(new File(path + branch))
                .readEnvironment().build();

        Ref ref = repository.findRef(branch);

        System.out.println(ref);
    }

    public static void cloneViaProcessBuilder() throws IOException {

        String branch = "PayPal";
        String url = "https://github.com/devpune/TestRepo.git";
        String pat = "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";
//        String url = "https://github.com/stantry-tpt/PDM_braze.git";
//        String pat = "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";
        String cloneDir = "C:\\Work\\Development\\Test Repo\\jgit\\";
        String repoWithPatToken = url.replace("github.com", pat + "@github.com");

        System.out.println(repoWithPatToken);
        System.out.println("current thread: " + Thread.currentThread().getName());

        List<String> commands = Arrays.asList("git", "clone", "-v", "--depth","1",repoWithPatToken, branch, "-b", branch, "--single-branch");

        Process process = new ProcessBuilder().command(commands).directory(new File(cloneDir)).redirectErrorStream(true).start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String absolutePath = cloneDir + branch;
//        FileUtils.forceDelete(new File(absolutePath));
    }

    public static void commitViaProcessBuilder() throws IOException {

        String branch = "PayPal";
        String url = "https://github.com/devpune/TestRepo.git";
        String pat = "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";
        String cloneDir = "C:\\Work\\Development\\Test Repo\\jgit\\PayPal";
        String repoWithPatToken = url.replace("github.com", pat + "@github.com");

        System.out.println(repoWithPatToken);
        System.out.println("current thread: " + Thread.currentThread().getName());
        String commitMessage = "This is the commit message";
        List<String> commands = Arrays.asList("git", "commit", "-am", commitMessage);

        Process process = new ProcessBuilder().command(commands).directory(new File(cloneDir)).redirectErrorStream(true).start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String absolutePath = cloneDir + branch;
//        FileUtils.forceDelete(new File(absolutePath));
    }

    public static void pushViaProcessBuilder() throws IOException {

        String branch = "PayPal";
        String url = "https://github.com/devpune/TestRepo.git";
        String pat = "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";
        String cloneDir = "C:\\Work\\Development\\Test Repo\\jgit\\PayPal";
        String repoWithPatToken = url.replace("github.com", pat + "@github.com");

        System.out.println(repoWithPatToken);
        System.out.println("current thread: " + Thread.currentThread().getName());

        List<String> commands = Arrays.asList("git", "push", "-v");

        Process process = new ProcessBuilder().command(commands).directory(new File(cloneDir)).redirectErrorStream(true).start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String absolutePath = cloneDir + branch;
//        FileUtils.forceDelete(new File(absolutePath));
    }

    public static void setRemoteOrigin() throws IOException {

//        remote.origin.url=https://github.com/devpune/TestRepo.git
        String remoteOrigin = "https://github.com/devpune/TestRepo.git";
        List<String> commands = Arrays.asList("git", "remote", "set-url", "origin", remoteOrigin);
        String repoPath = "C:\\Work\\Development\\Test Repo\\jgit\\gyThree";
        Process process = new ProcessBuilder().command(commands).directory(new File(repoPath)).redirectErrorStream(true).start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getCommitHash() throws Exception {

        String path = "C:\\Work\\Development\\Git_Hub_Local_Repos\\repository_export\\TestRepo\\gyThree";
        List<String> commands = Arrays.asList("git", "log", "-1", "--pretty=format:%H");
        try (ScriptExecutor executor = new ScriptExecutor()) {
            executor.setDirectory(path).executeScript(commands).printScriptLogs();
        }
    }

    public static void getCommitHashHead(File repoPath) {
        FileRepositoryBuilder builder = null;
        Repository repository = null;

        try {
            builder = new FileRepositoryBuilder();
            repository = builder.setWorkTree(repoPath).readEnvironment().build();
            System.out.println(repository.resolve("HEAD^{tree}").getName());
        } catch (Exception e) {
//            Logger.error(e);
        } finally {
            if (repository != null) {
                repository.close();
            }
        }
    }

    public static void executeScript() throws Exception {
        String command = "C:\\Work\\gitTest.bat";
        try (ScriptExecutor scriptExecutor = new ScriptExecutor()) {
            scriptExecutor.executeScript(command).printScriptLogs();
        }
    }

    public static void jgitClone() {
        CloneCommand cc = null;
        Git git = null;
//        String gitHubUrl = "https://github.com/iZettle/global-link-test";
        String gitHubUrl = "https://oauth2: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@github.com/iZettle/global-link-test.git";
//        String gitHubUrl = "https://github.com/devpune/TestRepo.git";
        String userName = "devpune";
        String password = "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";
        String cloneDir = "C:\\Work\\Development\\Test Repo\\jgit\\vinayak123\\dev";
//        String cloneDir = "Repository/paypal-zettle/export/prod";
//        String branch = "gyThree";
        String branch = "main";
        File newBranchPath = new File(cloneDir + branch);
//        cc = Git.cloneRepository().setURI(gitHubUrl).setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password)).setDirectory(newBranchPath).setBare(false).setNoCheckout(false).setBranchesToClone(Arrays.asList("refs/heads/" + branch)).setBranch("refs/heads/" + branch);
        cc = Git.cloneRepository().setURI(gitHubUrl).setDirectory(newBranchPath)
                .setBare(false).setNoCheckout(false).setBranchesToClone(Arrays.asList("refs/heads/" + branch)).setBranch("refs/heads/" + branch);
        try {
            git = cc.call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        } finally {
            git.close();
        }
    }

    public static void jgitPull() {
        try {
            PullResult pullResult = null;
            Git git = null;
            String path = "C:\\Work\\Development\\Test Repo\\jgit\\vinayak123\\dev";
            String branch = "main";
            File sourceFolderFile = new File(path + branch);


            FileRepositoryBuilder builder = new FileRepositoryBuilder();
            Repository repository = builder.setWorkTree(sourceFolderFile).readEnvironment().build();
            git = new Git(repository);
            git.clean().setCleanDirectories(true).call();
            git.stashCreate().call();

            String userName = "devpune";
            String password = "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";

//            pullResult = git.pull()
//                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(
//                            userName, password))
//                    .setRemoteBranchName(branch).call();
            pullResult = git.pull().setRemoteBranchName(branch).call();
            System.out.println(pullResult);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void jgitAdd() {
        try {
            String path = "C:\\Work\\Development\\Test Repo\\jgit\\";
            String branch = "PS-TEST";
            FileRepositoryBuilder builder = new FileRepositoryBuilder();
            Repository repository = builder.setWorkTree(new File(path + branch)).readEnvironment()
                    .build();
            Git git = new Git(repository);
            git.add().addFilepattern(".").call();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void jgitPush() {
        try {
            Iterable<PushResult> pushResult = null;
            Git git = null;
            String path = "C:\\Work\\Development\\Test Repo\\jgit\\";
            String branch = "PS-TEST";
            File sourceFolderFile = new File(path + branch);

            FileRepositoryBuilder builder = new FileRepositoryBuilder();
            Repository repository = builder.setWorkTree(sourceFolderFile).readEnvironment().build();
            git = new Git(repository);
            git.clean().setCleanDirectories(true).call();
            git.stashCreate().call();
            pushResult = git.push().call();
            System.out.println(pushResult);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map<String, String> getTranslatableObjectsFromBranch() throws Exception {

        Map<String, String> map = new LinkedHashMap<>();
        FileRepositoryBuilder builder = new FileRepositoryBuilder();

        Repository repository = null;
        Ref ref = null;

        try {
            repository = builder.setWorkTree(new File("C:\\Work\\Development\\Git_Hub_Local_Repos\\repository_export\\PayPal")).readEnvironment().build();
            ref = repository.findRef("PayPal");
        } catch (Exception e) {
            throw new Exception();
        }
        if (ref == null) {
            throw new Exception();
        }
        ObjectId objId = ref.getObjectId();//objectId is the commit hash
        TreeWalk walk = null;
        RevWalk revWalk = null;
        try {
            revWalk = new RevWalk(repository);
            revWalk.setRetainBody(false);
            RevCommit commit = revWalk.parseCommit(objId);
            RevTree tree = commit.getTree();
            walk = new TreeWalk(repository);
            walk.addTree(tree);
            walk.setRecursive(true);

            List<TreeFilter> treeFilters = new ArrayList<>();

            if (!treeFilters.isEmpty()) {
                if (treeFilters.size() > 1) {
                    TreeFilter tf = AndTreeFilter.create(treeFilters);
                    walk.setFilter(tf);
                } else {
                    walk.setFilter(treeFilters.get(0));
                }
            }

            while (walk.next()) {
                FileMode fileMode = walk.getFileMode(0);
                ObjectId objectId = walk.getObjectId(0);
                map.put(walk.getPathString(), objectId.name());
            }

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (repository != null)
                repository.close();

            if (walk != null)
                walk.close();

            if (revWalk != null)
                revWalk.close();
        }

        return map;
    }

    private static Map<String, String> map = new HashMap<>();

    public static Map<String, String> getFilesWithoutJgit() {
        String path = "C:\\Work\\Development\\Git_Hub_Local_Repos\\repository_export\\PayPal";
        try {
            Map<String, String> newMap = new HashMap<>();
            Files.find(Paths.get(path), Integer.MAX_VALUE,
                            (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .map(fileAttr -> fileAttr.toString())
                    .filter(fileAttr -> !fileAttr.toString().contains(".git"))
                    .filter(fileAttr -> fileAttr.toString().contains("/mobile/cms"))//path filter
                    .filter(file -> file.toString().endsWith(".dcr"))//suffixes
                    .forEach(filePath -> createMap(filePath, newMap));

            System.out.println("New Map: " + newMap.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static void createMap(String filePath, Map<String, String> map) {
        filePath = filePath.replace("C:\\Work\\Development\\Git_Hub_Local_Repos\\repository_export\\PayPal\\", "").replace("\\", "/");
        map.put(filePath, "wqlrjhiserg");
    }

    public static void getDiff() throws IOException, GitAPIException {
        String repoPath = "C:\\Work\\Development\\Test Repo\\jgit\\PayPal";
        File sourceFolderFile = new File(repoPath);
        Repository repository = new FileRepositoryBuilder().setWorkTree(sourceFolderFile).readEnvironment().build();
        Git git = new Git(repository);
        ObjectId latestCommitHash = repository.resolve("HEAD^{tree}");
        String objString = ObjectId.toString(latestCommitHash);
        String obtStr = latestCommitHash.toString();
//        ObjectId oldCommitHash = repository.resolve("be40b1b419c0bffb42abd443e298310165ca69a1");
        ObjectId oldCommitHash = ObjectId.fromString("6a7918f6def38a32784c4a70f74cf2c30a71903c");
        ObjectReader reader = repository.newObjectReader();
        CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
        oldTreeIter.reset(reader, oldCommitHash);
        CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
        newTreeIter.reset(reader, latestCommitHash);
        List<DiffEntry> diffs = git.diff().setNewTree(newTreeIter).setOldTree(oldTreeIter).call();
        System.out.println("DiffEntry List - " + diffs.size());
    }
}
