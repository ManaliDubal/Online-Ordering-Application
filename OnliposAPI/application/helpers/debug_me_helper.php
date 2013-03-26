<?php if(!defined('BASEPATH')) exit('No direct script access allowed');
/**
 * @package application/helpers
 * @version 2.2
 */	



	if(!function_exists('call_debug')) {
		/**
		 * Pass an array in the parameter.
		 * @author Kenneth "digiArtist_ph" P. Vallejos
		 * @param array $obj, $flag This is passed into the function for debugging purposes.
		 * @return null
		 */
		function call_debug($obj, $flag = TRUE) {
			
			// this is solely for debugging purposes					
			echo '<pre />';
			print_r($obj);
			
			if($flag)
				die();			
		}
	}


	if(!function_exists('on_watch')) {
		/**
		 * 
		 * Pass a string literals in the parameter that you want displayed.
		 * @author Kenneth "digiArtist_ph" P. Vallejos
		 * @param string $str This is passed into the function for debugging purposes.
		 * @return null
		 */
		function on_watch($str, $flag = TRUE) {
			
			echo 'Watch: ' . $str . '<br />';
			
			if($flag)
				die();		
		}
	}