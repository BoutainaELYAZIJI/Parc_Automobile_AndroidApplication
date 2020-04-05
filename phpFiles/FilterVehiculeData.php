<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

include 'conc.php';

 $ID= $_POST['vehiculeID'];

$sql = "SELECT * FROM vehicules where id =".$ID;

$result = $conn->query($sql);

if ($result->num_rows >0) {
 
 
 while($row[] = $result->fetch_assoc()) {
 
 $tem = $row;
 
 $json = json_encode($tem);
 
 }
 
} else {
 echo "No Results Found.";
}
 echo $json.$ID;

$conn->close();
}
?>