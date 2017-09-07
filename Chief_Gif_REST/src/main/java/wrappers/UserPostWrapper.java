package wrappers;

public class UserPostWrapper {
    Integer userId;
    Integer postId;

    public UserPostWrapper() {
    }

    public UserPostWrapper(Integer userId, Integer postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
