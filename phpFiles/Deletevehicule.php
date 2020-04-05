<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'conc.php';


 $ID = $_POST['vehiculeID'];

$Sql_Query = "DELETE FROM vehicules WHERE id ='$ID';";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Record Deleted Successfully';
}
else
{
 echo 'Something went wrong';
 }

 mysqli_close($con); }
?>