 <?php
 
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'conc.php';

 $V_Immatriculation = $_POST['Immatriculation'];
 $V_Categorie= $_POST['Categorie'];
 $V_Marque= $_POST['Marque'];
$V_Marquedecarburant = $_POST['Marquedecarburant'];
$Sql_Query = "INSERT INTO vehicules (Immatriculation,Categorie,Marque,Marquedecarburant) values ('$V_Immatriculation','$V_Categorie','$V_Marque','$V_Marquedecarburant');";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Vehicule Registered Successfully';
}
else
{
 echo 'Something went wrong';
 }
 }
 mysqli_close($con);
?>