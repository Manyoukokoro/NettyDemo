package com.nekotori.entity.user;

public class UserDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.user_name
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.user_id
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    private String userId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.user_name
     *
     * @return the value of user_info.user_name
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.user_name
     *
     * @param userName the value for user_info.user_name
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.user_id
     *
     * @return the value of user_info.user_id
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.user_id
     *
     * @param userId the value for user_info.user_id
     *
     * @mbg.generated Tue Sep 15 16:52:55 CST 2020
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}