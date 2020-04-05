<?php
require"conc.php";


$Email=isset($_POST['email']) ? $_POST['email'] : NULL;
$password=isset($_POST['pass']) ? $_POST['pass'] : NULL;
$sql_query="select Firstname from users where Email like '$Email' and Password like '$password';";
$result=mysqli_query($con,$sql_query);

if (mysqli_num_rows($result)>0)
 {
$row=mysqli_fetch_assoc($result);
$Firstname=$row['Firstname'];
echo "Login Success...Welcome ".$Firstname;
}else{

echo "Login Failed..Try Again..";

}

?>