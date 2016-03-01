
		function threeSelect(config){
			var $s1=$("#"+config.province);
			var $s2=$("#"+config.city);
			var $s3=$("#"+config.district);
			var v1=config.provinceValue?config.provinceValue:null;
			var v2=config.cityValue?config.cityValue:null;
			var v3=config.districtValue?config.districtValue:null;
			getProvince(config);
			$s1.change(function(){
				clearOptions($s2);
				if(this.selectedIndex==-1 || this.options[this.selectedIndex].value==""){
					$s2.change();
					return;
				} 
				var s1_curr_val = this.options[this.selectedIndex].value;
				$.ajax({
					type:'post',
					url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentName.action',
					dataType:'json',
					async:false,
					data:{"parentName":$s1.val()},
					success:function(data){
						var cityData = data.split('\,');
						for(i=0;i<cityData.length-1;i++){
							$s2.append("<option value='"+cityData[i]+"'>"+cityData[i]+"</option>");
						}
					}
				});
				$s2.change();
			});
			$s2.change(function(){
				clearOptions($s3);
				var s1_curr_val = $s1[0].options[$s1[0].selectedIndex].value;
				if(this.selectedIndex==-1 || this.options[this.selectedIndex].value=="") return;
				var s2_curr_val = this.options[this.selectedIndex].value;
				$.ajax({
					type:'post',
					url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentNameAndPId.action',
					dataType:'json',
					async:false,
					data:{"parentName":$s1.val()+","+$s2.val()},
					success:function(data){
						var districtData = data.split('\,');
						for(i=0;i<districtData.length-1;i++){
							$s3.append("<option value='"+districtData[i]+"'>"+districtData[i]+"</option>");
						}
					}
				});
			});
			
		}

		
		function appendOptionTo($o,k,v,d){
			var $opt=$("<option>").text(k).val(k);
			if(k==d){$opt.attr("selected", "selected")}
			$opt.appendTo($o);
		}
		
		function clearOptions($o){
			$o.html("");
			appendOptionTo($o,"","","","");
		}
		
		//省市县三级联动测试
		function getProvince(config){
			$.ajax({
				type:'post',
				url:'/baseinfo/permanentAddressManage/getPermanentAddress.action',
				dataType:'json',
				async:false,
				success:function(data){
					clearOptions($("#"+config.province));
					clearOptions($("#"+config.city));
					clearOptions($("#"+config.district));
					var provinceData = data.split('\,');
					for(i=0;i<provinceData.length-1;i++){
						$("#"+config.province).append("<option value='"+provinceData[i]+"'>"+provinceData[i]+"</option>");
					}
					if($("#"+config.province+" option").size()>1){
						$("#"+config.province).val(config.provinceValue?config.provinceValue:null);
						getCity(config);
					}
				}
			});
		}
		function getCity(config){
			if(config==""||config.provinceValue==""){
				return;
			}
			$.ajax({
				type:'post',
				url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentName.action',
				dataType:'json',
				data:{"parentName":config.provinceValue},
				async:false,
				success:function(data){
					clearOptions($("#"+config.city));
					var cityValues = data.split('\,');
					for(i=0;i<cityValues.length-1;i++){
						$("#"+config.city).append("<option value='"+cityValues[i]+"'>"+cityValues[i]+"</option>");
					}
					if($("#"+config.city+" option").size()>1){
						$("#"+config.city).val(config.cityValue?config.cityValue:null);
						getDistrict(config);
					}
				}
			});
		}

		function getDistrict(config){
			$.ajax({
				type:'post',
				url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentNameAndPId.action',
				dataType:'json',
				async:false,
				data:{"parentName":config.provinceValue+","+config.cityValue},
				success:function(data){
					clearOptions($("#"+config.district));
					var districtValues = data.split('\,');
					for(i=0;i<districtValues.length-1;i++){
						$("#"+config.district).append("<option value='"+districtValues[i]+"'>"+districtValues[i]+"</option>");
					}
					$("#"+config.district).val(config.districtValue?config.districtValue:null);
				}
			});
		}

		function ajax(parentName,id,value){
			$.ajax({
				type:'post',
				url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentName.action',
				dataType:'json',
				async:false,
				data:{"parentName":parentName},
				success:function(data){
					clearOptions(id);
					var pro = data.split('\,');
					for(i=0;i<pro.length-1;i++){
						id.append("<option value='"+pro[i]+"'>"+pro[i]+"</option>");
					}
					id.val(value?value:null);
				}
			});
		}
		