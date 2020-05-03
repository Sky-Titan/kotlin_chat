<?php
$con= mysqli_connect("localhost", "root", "qkrwns12","kotlinchat");

if(mysqli_connect_errno($con))
{
    echo "Failed to connect to Mysql: ".mysqli_connect_error();
}

mysqli_set_charset($con, "utf8");

$res = mysqli_query($con, "select * from chatroom");
$result = array();

while($row = mysqli_fetch_array($res)){
    
    $res2 = mysqli_query($con, "select COUNT(*) from chatofuser where chat = ".$row[0]);
    $row2 = mysqli_fetch_row($res2);
    
    array_push($result, 
            array('chat_num'=>$row[0],'title'=>$row[1], 'chatroom_people'=>$row2[0]));
}
echo json_encode(array("result"=>$result));
mysqli_close($con);
?>