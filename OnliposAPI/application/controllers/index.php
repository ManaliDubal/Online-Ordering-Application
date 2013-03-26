<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Index extends CI_Controller {

	public function __construct() {
		parent::__construct();
			
	}

	public function index(){
		
	}
	
	public function select() {
		
	if(isset($_REQUEST['items'])) {

		if($_REQUEST['items'] == 'null') {
		
	
					$params['table'] = array('name' => 'items');
					$this->mdldata->select($params);
					$data = $this->mdldata->_mRecords;
					print(json_encode($data));
					//call_debug($data);
				
			} else {
			
					$params['table'] = array('name' => 'items', 'criteria_phrase' => 'shortname LIKE "%' .  $_REQUEST['items'] . '%"');										 					$this->mdldata->select($params);
					$data = $this->mdldata->_mRecords;
					print(json_encode($data));
		
				} 
		} else {
			
			$output = "[{'error':'Empty'}]";
			print($output);
			
			
		}
	}
	
	public function selectByCategory() {
			
			if(isset($_REQUEST['category'])) {
			
					if($_REQUEST['category'] == 'type') {
								
						$params['table'] = array('name' => 'item_type');
						$this->mdldata->select($params);
						$data = $this->mdldata->_mRecords;
						print(json_encode($data));
			
					
					
					} elseif($_REQUEST['category'] == 'Books') {

						$params['table'] = array('name' => 'items', 'criteria_phrase' => 'item_type=(select it_id from item_type where type_name like "%' . $_REQUEST['category'] . '%")');
						$this->mdldata->select($params);
						$data = $this->mdldata->_mRecords;
						print(json_encode($data));
						

					} elseif($_REQUEST['category'] == 'School Supplies') {
					

						$params['table'] = array('name' => 'items', 'criteria_phrase' => 'item_type=(select it_id from item_type where type_name LIKE "%' . $_REQUEST['category'] . '%")');
						$this->mdldata->select($params);
						$data = $this->mdldata->_mRecords;
						print(json_encode($data));

						
					} elseif($_REQUEST['category'] == 'T-Shirts') {

						$params['table'] = array('name' => 'items', 'criteria_phrase' => 'item_type=(select it_id from item_type where type_name like "%' . $_REQUEST['category'] . '%")');
						$this->mdldata->select($params);
						$data = $this->mdldata->_mRecords;
						print(json_encode($data));

						
					} elseif($_REQUEST['category'] == 'Novelty Items') {

						$params['table'] = array('name' => 'items', 'criteria_phrase' => 'item_type=(select it_id from item_type where type_name like "%' . $_REQUEST['category'] . '%")');
						$this->mdldata->select($params);
						$data = $this->mdldata->_mRecords;
						print(json_encode($data));

						
					} elseif($_REQUEST['category'] == 'Accessories') {
						
						$params['table'] = array('name' => 'items', 'criteria_phrase' => 'item_type=(select it_id from item_type where type_name like "%' . $_REQUEST['category'] . '%")');
						$this->mdldata->select($params);
						$data = $this->mdldata->_mRecords;
						print(json_encode($data));
						
					} else {
					echo "response empty";
					}
			
			} else {
			echo "empty";
			}
		}
		
	public function insert() {
		
		if(isset($_REQUEST['items'])) {
			
				$array = preg_split("/([\}\{\[\]])/", $_REQUEST['items']);
				
				// replaces = to =>
				$pattern = '/=/';
				$tmpArray = array();
				$cleandArray = array();
				$array = preg_replace($pattern, "=>", $array);
				
				foreach($array as $v) {
					if($v != "" && preg_match('/=>/',$v)):
						$tmpArray[] = $v;					
					endif;
				}
				
				
				$array = $tmpArray;
				
				foreach($array as $k => $v):
					$tmpArray = preg_split("/,/", $v);
					
					// remove single qoute
					$pattern1 = "/'/";
					$array = preg_replace($pattern1, "", $tmpArray);
				
						foreach($array as $val) {
							$keyVal = preg_split('/=>/',$val);
							
							$cleandArray[$k][trim($keyVal[0])] = trim($keyVal[1]);
						}
						
					
				endforeach;
				
				$total = 0;
				for($j=0; $j<count($cleandArray); $j++) {
					$total = $cleandArray[$j]['item_total'] + $total;
					
				}
				$customer = $cleandArray[0]['order_name'];
				// get the date and SQL Format
				$datestring = "Y-m-d g:i:s";
				$time = time();
				
				$params = array(
							'table' => array(
											'name' => 'orders'
											),
							'fields' => array(
											'cust_name' => $customer,
											'transaction_date' => mdate($datestring, $time),
											'total' => $total
											)
									);
									
					$this->mdldata->reset();
					$this->mdldata->insert($params);
				
					// get the last ID in the orders table
					$params['querystring'] = "SELECT * FROM orders ORDER BY or_id DESC LIMIT 1";
					$this->mdldata->select($params);
					$data = $this->mdldata->_mRecords;
					
					$cname = $data[0]->cust_name;
					$input = $data[0]->or_id;
					$orderNumber = str_pad($input, 8, "0", STR_PAD_LEFT);

				for($k=0; $k<count($cleandArray); $k++) {

					$this->mdldata->reset();
					$params['querystring'] = "INSERT INTO order_dts(item_id, qty, subtotal, unit_price, order_number, order_id) VALUES('". $cleandArray[$k]['item_id'] ."', '". $cleandArray[$k]['order_QTY'] ."', '". $cleandArray[$k]['item_total'] ."', '". $cleandArray[$k]['item_price'] ."', '" .  $orderNumber  . "', '" .  $input  . "')";
					$this->mdldata->insert($params);
					
				}
				
				$output = "[{'order_number':'" . $orderNumber . "', 'customer_name':'". $cname . "'}]";
				print($output);

					
	} else {
			$output = "[{'error':'Empty'}]";
			print($output);
	}
	}
}
