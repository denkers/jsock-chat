package com.kyleruss.jsockchat.commons.room;

import java.util.List;

public class AbstractRoom implements Room
{
    private List<String> userList;
    private String roomName;
    
    
    @Override
    public List<String> getUserList() {
    }

    @Override
    public String getRoomPassword() {
    }

    @Override
    public void setRoomPassword(String password) {
    }

    @Override
    public void setUserList(List<String> userList) {
    }

    @Override
    public void leaveRoom(String username) {
    }

    @Override
    public boolean joinRoom(String username) {
    }

    @Override
    public String getRoomName() {
    }

    @Override
    public int getNumUsersInRoom() {
    }

    @Override
    public RoomType getRoomType() {
    }

    @Override
    public boolean isPassProtected() {
    }
    
}
