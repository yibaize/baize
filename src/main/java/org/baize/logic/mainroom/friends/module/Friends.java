package org.baize.logic.mainroom.friends.module;

import io.netty.channel.Channel;
import org.baize.dao.model.Persist;
import org.baize.server.manager.Response;
import org.baize.utils.ProtostuffUtils;

import java.util.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/9.
 * 描述：
 */
public class Friends extends Persist {
    private Set<Integer> friendIds;
    private Map<Integer,List<String>> chatMsg;
    private Set<Integer> apply;//申请列表
    private boolean hasNewFriend;
    public Friends() {
        friendIds = new HashSet<>();
        chatMsg = new HashMap<>();
        apply = new HashSet<>();
        hasNewFriend = false;
    }

    public Set<Integer> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(Set<Integer> friendIds) {
        this.friendIds = friendIds;
    }

    public Map<Integer, List<String>> getChatMsg() {
        return chatMsg;
    }

    public void setChatMsg(Map<Integer, List<String>> chatMsg) {
        this.chatMsg = chatMsg;
    }

    public Set<Integer> getApply() {
        return apply;
    }

    public void setApply(Set<Integer> apply) {
        this.apply = apply;
    }

    public boolean isHasNewFriend() {
        return hasNewFriend;
    }

    public void setHasNewFriend(boolean hasNewFriend) {
        this.hasNewFriend = hasNewFriend;
    }

    public Set<Integer> apply() {
        if(this.apply == null)
            apply = new HashSet<>();
        return apply;
    }
    public Map<Integer, List<String>> chatMsg() {
        if(this.chatMsg == null)
            chatMsg = new HashMap<>();
        return chatMsg;
    }
    public Set<Integer> friendIds() {
        if(this.friendIds == null)
            friendIds = new HashSet<>();
        return friendIds;
    }
    public boolean add(int id){
        if(!friendIds.contains(id) || !apply.contains(id)) {
            friendIds.add(id);
            hasNewFriend = false;
            return true;
        }
        return false;
    }
    public boolean apply(int id){
        if(!friendIds.contains(id) || !apply.contains(id)) {
            apply.add(id);
            hasNewFriend = true;
            return true;
        }
        return false;
    }
    public boolean remove(int id){
        if(friendIds.contains(id)) {
            friendIds.remove(id);
            if (apply.contains(id))
                apply.remove(id);
            return true;
        }
        return false;
    }
    public void chat(int id,String msg){
        if(chatMsg.containsKey(id))
            chatMsg.get(id).add(msg);
        else{
            List<String> m = new ArrayList<>();
            m.add(msg);
            chatMsg.put(id,m);
        }
    }
    public void removeChat(int id){
        if(chatMsg.containsKey(id))
            chatMsg.remove(id);
    }
    public void notifyx(short id,int playerId, Channel ctx){
        Response response = new Response();
        response.setId(id);
        byte[] buf = ProtostuffUtils.serializer(playerId);
        response.setData(buf);
        ctx.writeAndFlush(response);
    }
}
