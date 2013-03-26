<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Admin extends CI_Controller {

	public function __construct() {
		parent::__construct();
			
	}
	
	public function index() {
		
		$this->load->view('login_view_admin');
		
	}
	
	public function login_validate() {
				$validation = $this->form_validation;
		
		// sets rules
		$validation->set_rules('uname', 'Username', 'required');
		$validation->set_rules('pword', 'Password', 'required|min_length[3]');
		
		if($validation->run() === FALSE) {
			$this->index();
		} else {
			if($this->isExists()) {
				
				 $params = array(
 							'admin_uname' => $this->input->post('uname'),
 							'admin_islog' => TRUE,
							'admin_fullname' => ' Web Administrator '
 						);
		
				$this->sessionbrowser->setInfo($params); // returns TRUE if successful, otherwise FALSE	

				redirect(base_url() . 'admin/orderList');
			} else {
				$this->index();
			}
		}	
	}
	
	private function isExists() {
		
		// loads mdldata class
		$this->load->model('mdldata');
		
		$params['table'] = array('name' => 'accounts', 'criteria_phrase' => 'uname="' . $this->input->post('uname') . '" and pword="' . md5($this->input->post('pword')) . '"');
		
		// $this->mdldata->SQLText(TRUE);
		$this->mdldata->select($params);
		//$qryInsertList = $this->mdldata->buildQueryString(); 
		
		//call_debug($qryInsertList);
		
		if($this->mdldata->_mRowCount < 1)
			return FALSE;
		
		$this->_mUser = $this->mdldata->_mRecords;
		
		return TRUE;	
	}

	public function orderList(){
		
		
		
		$params = array('admin_uname', 'admin_islog', 'admin_fullname');		
				if($this->sentinel->goFlag($params)) {
					//$params['table'] = array('name' => 'orders', 'order_by' => 'transaction_date:desc');
					$params['querystring'] = 'SELECT * FROM `orders` left join order_dts on orders.or_id=order_dts.ordts_id ORDER BY transaction_date desc';
					$this->mdldata->select($params);
					$data['orders'] = $this->mdldata->_mRecords;
					//call_debug($data);
					$this->load->view("admin_view", $data); }
 				else {
 					$this->index();
		
				}
	}
	
	
	public function customerItem() {	
		
				$params = array('admin_uname', 'admin_islog', 'admin_fullname');		
				if($this->sentinel->goFlag($params)) {
					
					$pattern = '/%20/';
					//call_debug($this->uri->segment(3));
					
					$or_id = preg_replace($pattern, " " , trim($this->uri->segment(3)));
					$params['querystring'] = 'select orders.cust_name, orders.total, order_dts.qty, order_dts.unit_price, items.shortname, order_dts.subtotal from orders left join order_dts  on orders.or_id=order_dts.order_id right join items on order_dts.item_id=items.i_id where orders.or_id="'. $or_id  .'"';
					//call_debug($params);
					$this->mdldata->select($params);
					$data['records'] = $this->mdldata->_mRecords;
					
					$params['table'] = array('name' => 'orders', 'criteria_phrase' => 'cust_name="' .  $or_id . '"');
					$this->mdldata->reset();
					$this->mdldata->select($params);
					$data['orderTotal'] = $this->mdldata->_mRecords;
					
					
					
					//call_debug($data);
					$this->load->view("admin_view_customerItem", $data); }
		else {
			
					$this->index();
					
		}
	}
	
	public function logout() {
		
				$params = array('admin_uname', 'admin_islog', 'admin_fullname');
				
				$this->sessionbrowser->destroy($params); // returns TRUE if successful, otherwise FALSE
				
				$this->index();
		
	}
}
