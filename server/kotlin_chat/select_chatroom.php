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
    
    array_push($result, 
            array('chat_num'=>$row[0],'title'=>$row[1]));
}
echo json_encode(array("result"=>$result));
mysqli_close($con);
?>