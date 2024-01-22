package com.example.test.gitplugin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public class WebClientServiceExecutor {
    public static void main(String[] args) throws Exception {

        String url = "https://api.github.com/repos/devpune/TestRepo/branches/PayPal2";
        String patToken = "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";

        StringUtils.isNotBlank("");

//        getGitHubBranch(url, patToken);

        url = "https://api.github.com/repos/devpune/TestRepo";
        PullRequest pullRequest = getPRDetailsByBaseAndHead(url, patToken,
                "PayPal", "pr-get-test", "open");
        System.out.println(pullRequest);
    }


    public static PullRequest getPRDetailsByBaseAndHead(String url, String patToken, String base,
                                                        String head, String state) throws JsonProcessingException {
        WebClient client = null;
        String response = "";
        try {
            url += "/pulls?state=" + state + "&base=" + base + "&head=" + head;
            client = WebClientFactory.getClient();
            response = client.get().uri(url).header(HttpHeaders.AUTHORIZATION, bearer(patToken)).retrieve()
                    .onStatus(HttpStatus::isError, WebClientServiceExecutor::handleErrorResponse)
                    .bodyToMono(String.class).block();

            System.out.println("Success:" + new ObjectMapper().writeValueAsString(response));

            List<PullRequest> pullRequests = new ObjectMapper().readValue(response, new TypeReference<List<PullRequest>>() {
            });

            Optional<PullRequest> optional = pullRequests.stream().filter(pullRequest -> {
                return pullRequest.getState().equals(state) &&
                        pullRequest.getHead().getRef().equals(head) &&
                        pullRequest.getBase().getRef().equals(base);
            }).findFirst();

            PullRequest res = new PullRequest();
            res.setBody("PR already raised");

            return optional.orElseGet(PullRequest::new);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static GitHubBranch getGitHubBranch(String url, String patToken) {
        WebClient client = null;
        String response = "";
        GitHubBranch branch = null;
        try {
            client = WebClientFactory.getClient();
            response = client.get().uri(url).header(HttpHeaders.AUTHORIZATION, bearer(patToken)).retrieve()
                    .onStatus(HttpStatus::isError, WebClientServiceExecutor::handleErrorResponse)
                    .bodyToMono(String.class).block();

            System.out.println("Success:" + new ObjectMapper().writeValueAsString(response));
            branch = new ObjectMapper().readValue(response, GitHubBranch.class);
            return branch;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            branch = new GitHubBranch();
            branch.setErrorMessage(e.getMessage());
//            throw e;
        }

        return branch;
    }

    private static String bearer(String patToken) {
        return "Bearer " + patToken;
    }

    private static Mono<? extends Throwable> handleErrorResponse(ClientResponse response) {
        if (response.statusCode() == HttpStatus.NOT_FOUND) {
            return response.bodyToMono(String.class)
                    .flatMap(errorBody -> Mono.error(new Exception("Request failed with status code: " + response.statusCode() + ", Error: " + errorBody)));
        } else {
            return Mono.error(new Exception("Request failed with status code: " + response.statusCode()));
        }
    }

}


@JsonIgnoreProperties(ignoreUnknown = true)
class GitHubBranch {
    @JsonProperty("name")
    private String name;
    private String errorMessage;

    @Override
    public String toString() {
        return "GitHubBranch [name=" + name + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Head {
    private String label;
    private String ref;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Base {
    private String label;
    private String ref;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PullRequest {
    private String url;
    private String body;
    private Head head;
    private Base base;
    private int commits;
    private int additions;
    private int deletions;
    private String state;
    private int number;
    @JsonProperty("issue_url")
    private String issueUrl;
    @JsonProperty("merged_at")
    private String mergedAt;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCommits() {
        return commits;
    }

    public void setCommits(int commits) {
        this.commits = commits;
    }

    public int getAdditions() {
        return additions;
    }

    public void setAdditions(int additions) {
        this.additions = additions;
    }

    public int getDeletions() {
        return deletions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }

    public String getMergedAt() {
        return mergedAt;
    }

    public void setMergedAt(String mergedAt) {
        this.mergedAt = mergedAt;
    }
}
