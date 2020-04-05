 <?php
 
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'conc.php';

 $D_CIN = $_POST['CIN'];
 $D_Nom = $_POST['Nom'];
 $D_prénom = $_POST['prénom'];
$D_phone = $_POST['phone'];
$Sql_Query = "INSERT INTO chaffeurs (CIN,Nom,prénom,phone) values ('$D_CIN','$D_Nom','$D_prénom','$D_phone');";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Drivers Registered Successfully';
}
else
{
 echo 'Something went wrong';
 }
 }
 mysqli_close($con);
?>