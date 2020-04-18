
<?php
$con=mysqli_connect("localhost","root","qkrwns12","kotlinchat");
 
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
 mysqli_set_charset($con, "utf8");
//$number = $_GET['number'];
$result=array();
$sql="SELECT * FROM chatofuser where user='";

    $user=$_GET['user'];
    $sql=$sql.$user."'";


  $res = mysqli_query($con,$sql);
    while($row = mysqli_fetch_array($res)){
    
    array_push($result, 
            array('user'=>$row[0],'chat'=>$row[1]));
    }

echo json_encode(array("result"=>$result));
mysqli_close($con);
?>