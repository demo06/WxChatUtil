package com.wb.wxchatutil;

import java.util.ArrayList;

/**
 * @author WenBin
 * @version V1.0
 * @description
 * @date 2021/3/25
 */
public class UserInfo {
    private String groupName;
    private boolean friends;
    private boolean groups;
    private boolean privates;
    private int counts;
    private ArrayList<GroupMember> groupMembers;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isFriends() {
        return friends;
    }

    public void setFriends(boolean friends) {
        this.friends = friends;
    }

    public boolean isGroups() {
        return groups;
    }

    public void setGroups(boolean groups) {
        this.groups = groups;
    }

    public boolean isPrivates() {
        return privates;
    }

    public void setPrivates(boolean privates) {
        this.privates = privates;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public ArrayList<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(ArrayList<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    private class GroupMember {
        private String name;
        private boolean manage;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isManage() {
            return manage;
        }

        public void setManage(boolean manage) {
            this.manage = manage;
        }
    }
}
