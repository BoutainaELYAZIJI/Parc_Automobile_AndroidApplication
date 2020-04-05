 <?php

if($_SERVER['REQUEST_METHOD']=='POST'){

include 'conc.php';

$DriverID= $_POST['DriverID'];

$sql = "SELECT * FROM chauffeurs where id = '$DriverID'" ;

$result = $conn->query($sql);

if ($result->num_rows >0) {
 
 while($row[] = $result->fetch_assoc()) {
 
 $tem = $row;
 
 $json = json_encode($tem);
 
 }
 
} else {
 echo "No Results Found.";
}
 echo $json;

$conn->close();
}
?>