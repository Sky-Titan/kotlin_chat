<?php 

  $con=mysqli_connect("localhost","root","qkrwns12","kotlinchat");

    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {

        // 안드로이드 코드의 postParameters 변수에 적어준 이름을 가지고 값을 전달 받습니다.
        $user_ID=$_POST['user_ID'];
        $user_password=$_POST['user_password'];
        
        $sql="SELECT * FROM user where user_ID='";
        $sql=$sql.$user_ID."'";
        
        $res = mysqli_query($con,$sql);
        $isExist = false;
        
        while($row = mysqli_fetch_array($res)){
    
            //존재하는 user
            if(!strcmp($row[0],$user_ID))
            {
                $isExist = TRUE;
            }
          
        }
        $result=array();
        if($isExist)
        {
            
            array_push($result, 
                array('title'=>"fail",'cause'=>"user exist"));
            echo json_encode(array("result"=>$result));
               
        }
        else
        {
           if(!isset($errMSG)) // 이름과 나라 모두 입력이 되었다면 
            {
                try{
                    // SQL문을 실행하여 데이터를 MySQL 서버의 users 테이블에 저장합니다. 
                    $sql="INSERT INTO user(user_ID,user_password) VALUES('".$user_ID."','".$user_password."')";
                    $stmt = mysqli_prepare($con,$sql);
                   $result=array();
                    if($stmt->execute())
                    {  
                        array_push($result, 
                            array('title'=>"pass"));
                        echo json_encode(array("result"=>$result));
                    }
                    else
                    {
                        array_push($result, 
                            array('title'=>"fail", 'cause'=>"database error"));
                        echo json_encode(array("result"=>$result));
                    }
                   
                    

                } catch(PDOException $e) {
                    die("Database error: " . $e->getMessage()); 
                }
            }  
        }
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