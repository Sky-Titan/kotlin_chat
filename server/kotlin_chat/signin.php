<?php 

   $con=mysqli_connect("localhost","root","qkrwns12","kotlinchat");

    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {
        $result=array();
        $sql="SELECT * FROM user where user_ID='";

        $user_ID=$_POST['user_ID'];
        $user_password = $_POST['user_password'];
        $sql=$sql.$user_ID."'";
    


        $res = mysqli_query($con,$sql);
        if(mysqli_fetch_lengths($res)==0)//user 없음
          array_push($result, 
            array('title'=>"fail",'cause'=>'user not exist'));
       
        while($row = mysqli_fetch_array($res)){
    
            //존재안하는 user
            if(strcmp($row[0],$user_ID))
            {
                array_push($result, 
                array('title'=>"fail",'cause'=>'user not exist'));
            }
            //비밀번호 불일치
            else if(strcmp($row[1], $user_password))
            {
                array_push($result, 
                array('title'=>"fail",'cause'=>"password wrong"));
            }
            else if(!strcmp($row[0],$user_ID) && !strcmp($row[1], $user_password))//로그인 완료
            {
                array_push($result, 
                array('title'=>"pass"));
            }
            else
            {
                array_push($result, 
                array('title'=>"fail",'cause'=>'user not exist'));
            }
        }

    echo json_encode(array("result"=>$result));
    mysqli_close($con);

   }

?>





<?php 
    if (isset($errMSG)) echo $errMSG;
    if (isset($successMSG)) echo $successMSG;

	$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
   
    if( !$android )
    {
?>
    <html>
       <body>

            <form action="<?php $_PHP_SELF ?>" method="POST">
               
                user_id: <input type = "text" name = "user_ID" />
                user_password: <input type = "text" name = "user_password" />
                <input type = "submit" name = "submit" />
            </form>
       
       </body>
    </html>

<?php 
    }
?>