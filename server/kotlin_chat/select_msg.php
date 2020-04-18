
<?php
$con=mysqli_connect("localhost","root","qkrwns12","kotlinchat");
 
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
 mysqli_set_charset($con, "utf8");
//$number = $_GET['number'];
$result=array();
$sql="SELECT * FROM message where chat_num=";

$chat_num=$_GET['chat_num'];
$sql=$sql.$number;
$sender=" AND sender='".$_GET['sender'];
$sql=$sql.$sender."'";

  $res = mysqli_query($con,$sql);
    while($row = mysqli_fetch_array($res)){
    
    array_push($result, 
            array('msg_num'=>$row[0], 'content'=>$row[1], 'send_time'=>$row[2], 'sender'=>$row[3], 'chat_num'=>$row[4]));
    }

echo json_encode(array("result"=>$result));
mysqli_close($con);
?>