package com.bfmanager.util;

public class JavaScriptUtil {
	
	public String radarChart(String canvas, String[] labels, String[] data){		
		String jsLabel="";
		for (int i = 0; i < labels.length; i++) {
			jsLabel=jsLabel+"'"+labels[i]+"'"+((i+1)<labels.length?",":"");			
		}
		String jsData="";
		for (int i = 0; i < data.length; i++) {
			jsData=jsData+data[i]+((i+1)<data.length?",":"");			
		}
		
		String jsRadar="new Chart("+
							"document.getElementById('"+canvas+"'),{"+
							"'type':'radar',"+
							"'data':{"+
								"'labels':["+jsLabel+"],"+
								"'datasets':[{"+
		 						//	"'label':'Resultado',"+
		 							"'data':["+jsData+"],"+
		 							"'fill':true,"+
		 							"'backgroundColor':'rgba(54, 162, 235, 0.2)',"+
		 							"'borderColor':'rgb(54, 162, 235)',"+
		 							"'pointBackgroundColor':'rgb(54, 162, 235)',"+
		 							"'pointBorderColor':'#fff',"+
		 							"'pointHoverBackgroundColor':'#fff',"+
		 							"'pointHoverBorderColor':'rgb(54, 162, 235)'"+
		 						"}]"+
		 					"},"+
		 					"'options':{"+
			 					"'animation': {"+	 				            
		 				            "'onComplete': function () {"+
		 				                "var chartInstance = this.chart,"+
		 				                "ctx = chartInstance.ctx;"+	
		 				                "ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);"+
		 				                "ctx.textAlign = 'center';"+
		 				                "ctx.textBaseline = 'bottom';"+	
		 				                "this.data.datasets.forEach(function (dataset, i) {"+
		 				                    "var meta = chartInstance.controller.getDatasetMeta(i);"+
		 				                    "meta.data.forEach(function (bar, index) {"+
		 				                        "var data = dataset.data[index];"+                            
		 				                        "ctx.fillText(data, bar._model.x, bar._model.y - 5);"+
		 				                    "});"+
		 				                "});"+
		 				            "}"+
		 				        "},"+
			 					"'legend': {"+
			 				        "'display': false"+
			 				    "},"+
									"'elements':{"+
										"'line':{"+
											"'tension':0,'borderWidth':3"+
										"}"+
									"},"+
				 					"'scale':{"+		        
				 				        "'display': true,"+
				 				        "'ticks': {"+
				 				        	"'display': false,"+
				 				        	"'beginAtZero': true,"+
				 				        	"'min': 0,"+
				 				        	"'max': 1"+
				 				        "}"+
			 						"}"+
								"}"+
						"}"+
					");";
		return jsRadar;
	}
}
