<?php

require"conc.php";

$Firstname=isset($_POST['Firstname']) ? $_POST['Firstname'] : NULL;
$Lastname=isset($_POST['Lastname']) ? $_POST['Lastname'] : NULL;
$Email=isset($_POST['Email']) ? $_POST['Email'] : NULL;
$password=isset($_POST['Password']) ? $_POST['Password'] : NULL;
$ConfirmPass=isset($_POST['ConfirmPass']) ? $_POST['ConfirmPass'] : NULL;

$sql_query="insert into users values('$Firstname','$Lastname','$Email','$password','$ConfirmPass');";
if (mysqli_query($con,$sql_query)) {
 echo"Data Insertion Success...";
}else{
echo "Data insertion error..".mysqli_error($con);
}

?>