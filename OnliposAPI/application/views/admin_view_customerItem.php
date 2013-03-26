<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<?php echo base_url();?>css/member.css" />
<title>Untitled Document</title>
</head>

<body>
<?php 
 
	 echo "<br /><br />" . $records[0]->cust_name . "<br /><br />";
 
?>

<?php echo "<table border='1'>"; ?>
		<?php $rowAlternate = 0; ?>
			<?php foreach($records as $item): ?>         
				<?php if (($rowAlternate %2) == 0): ?>
				        <?php 	echo "<tr class='even'><td>";   ?>
                        <?php echo $item->shortname  ?>
				        <?php 	echo "</td><td>";  ?>
				        <?php echo $item->qty  ?>
				        <?php 	echo "</td><td>";  ?>
				        <?php 	echo $item->unit_price  ?> 
                        <?php 	echo "</td><td>";  ?>
				        <?php 	echo $item->subtotal  ?>         
				        <?php 	echo "</td></tr>";  ?>
			        <?php else: ?>
			        	<?php 	echo "<tr class='odd'><td>";   ?>
                        <?php echo $item->shortname  ?>
				        <?php 	echo "</td><td>";  ?>
				        <?php echo $item->qty  ?>
				        <?php 	echo "</td><td>";  ?>
				        <?php 	echo $item->unit_price  ?> 
                        <?php 	echo "</td><td>";  ?>
				        <?php 	echo $item->subtotal  ?>         
				        <?php 	echo "</td></tr>";  ?>
			        
			        <?php endif; ?>
			        <?php $rowAlternate++; ?>
		    <?php endforeach; ?>
           <?php $grandTotal = 0;
		 foreach($records as $total) {
			 
			$grandTotal =  $total->subtotal + $grandTotal;
			 
		 } ?>
         <tr><td></td><td></td><td class="total">Total</td><td><label class="red"><?php echo "Php ". $grandTotal ?></label></td></tr>
		</table>
		<p><a href="<?php echo base_url(); ?>admin/orderList">Back to Order List</a></p>
</body>
</html>