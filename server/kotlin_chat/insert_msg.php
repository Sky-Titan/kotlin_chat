<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');


    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {

        // 안드로이드 코드의 postParameters 변수에 적어준 이름을 가지고 값을 전달 받습니다.
  
        $content=$_POST['content'];
        $send_time=$_POST['send_time'];
        $sender=$_POST['sender'];
        $chat_num=$_POST['chat_num'];
        if(!isset($errMSG)) // 이름과 나라 모두 입력이 되었다면 
        {
            try{
                // SQL문을 실행하여 데이터를 MySQL 서버의 테이블에 저장합니다. 
                $stmt = $con->prepare('INSERT INTO message(content,send_time, sender, chat_num) VALUES(:content,:send_time, :sender, :chat_num)');
          
                $stmt->bindParam(':content', $content);
                $stmt->bindParam(':send_time', $send_time);
                $stmt->bindParam(':sender', $sender);
                $stmt->bindParam(':chat_num', $chat_num);
                

                if($stmt->execute())
                {
                    $successMSG = "새로운 게시글을 추가했습니다.";
                }
                else
                {
                    $errMSG = "게시글 추가 에러";
                }

            } catch(PDOException $e) {
                die("Database error: " . $e->getMessage()); 
            }
        }

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

                 content: <input type = "text" name = "content" />
                  send_time: <input type = "text" name = "send_time" />
                   sender: <input type = "text" name = "sender" />
                    chat_num: <input type = "text" name = "chat_num" />

                <input type = "submit" name = "submit" />
            </form>
       
       </body>
    </html>

<?php 
    }
?>