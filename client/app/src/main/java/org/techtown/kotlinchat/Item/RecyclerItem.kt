package org.techtown.kotlinchat.Item

class RecyclerItem {

    lateinit var chatroom_ID: String;
    lateinit var chatroom_title: String;
    lateinit var chatroom_people:String;

    constructor(chatroom_ID: String, chatroom_title: String, chatroom_people: String) {
        this.chatroom_ID = chatroom_ID
        this.chatroom_title = chatroom_title
        this.chatroom_people = chatroom_people
    }

    constructor()
    {

    }

}