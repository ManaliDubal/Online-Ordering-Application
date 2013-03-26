<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="text/css" rel="stylesheet" href="<?php echo base_url();?>css/member.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>

<p><?php 
 			// getInfo();
 			$params = array('admin_fullname');
 			$this->sessionbrowser->getInfo($params); // returns TRUE if successful, otherwise FALSE
			$arr = $this->sessionbrowser->mData; // returns array 
			echo "Welcome ".$arr['admin_fullname'] . ",";
?>
<a href="<?php echo base_url(); ?>admin/logout" />Log Out</a></p>
<?php echo "<table border='1'>"; ?>
		<?php echo "<tr><th>Order Number</th> <th>Customer Name</th> <th>Total Price</th><th>Transaction Date</th></tr>"; ?>
		<?php $rowAlternate = 0; ?>
			<?php foreach($orders as $order): ?>
            <?php $orderNumber = str_pad($order->or_id, 8, "0", STR_PAD_LEFT) ?>
				<?php if (($rowAlternate %2) == 0): ?>
				        <?php 	echo "<tr class='even'><td>";   ?>
				        <?php 	echo "<a href='" . base_url() . "admin/customerItem/". $order->or_id  . "'>" . $order->order_number . "</a>"?>
				        <?php 	echo "</td><td>";  ?>
				        <?php 	echo "<a href='" . base_url() . "admin/customerItem/". $order->or_id  . "'>" . $order->cust_name . "</a>"?>
				        <?php 	echo "</td><td>";  ?>
				        <?php echo $order->total  ?>
				        <?php 	echo "</td><td>";  ?>
				        <?php 	echo getDateArr($order->transaction_date) . " " . getTimeArr($order->transaction_date)  ?>        
				        <?php 	echo "</td></tr>";  ?>
			        <?php else: ?>
			        	<?php 	echo "<tr class='odd'><td>";   ?>
				        <?php 	echo "<a href='" . base_url() . "admin/customerItem/". $order->or_id . "'>" . $order->order_number . "</a>"?>
				        <?php 	echo "</td><td>";  ?>				        
				        <?php 	echo "<a href='" . base_url() . "admin/customerItem/". $order->or_id . "'>" . $order->cust_name . "</a>"?>
				        <?php 	echo "</td><td>";  ?>
				        <?php echo $order->total  ?>
				        <?php 	echo "</td><td>";  ?>
				        <?php 	echo getDateArr($order->transaction_date) . " " . getTimeArr($order->transaction_date)  ?>        
				        <?php 	echo "</td></tr>";  ?>
			        
			        <?php endif; ?>
			        <?php $rowAlternate++; ?>
		    <?php endforeach; ?>
		</table>


</body>
</html>