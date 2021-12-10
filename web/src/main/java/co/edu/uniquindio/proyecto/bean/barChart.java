package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean(name ="barChart")
public class barChart {

        private SeguridadBean seguridadBean;

        private BarChartModel barModel;


        @PostConstruct
        public void init() {
                createBarModels();
        }

        public BarChartModel getBarModel() {
                return barModel;
        }

        public void setBarModel(BarChartModel barModel) {
                this.barModel = barModel;
        }

        private void createBarModels() {
                createBarModel();

        }

        private void createBarModel() {
                barModel = initBarModel();

                barModel.setTitle("Grafico de barras");
                barModel.setLegendPosition("ne");

                Axis xAxis =barModel.getAxis(AxisType.X);
                xAxis.setLabel("Mes");

                Axis yAxsis =barModel.getAxis(AxisType.Y);
                yAxsis.setLabel("Jobs");
                yAxsis.setMin(0);
                yAxsis.setMax(200);
        }

        private BarChartModel initBarModel() {

                BarChartModel model =new BarChartModel();


                ChartSeries java= new ChartSeries();
                java.setLabel("Mes" );
                java.set("Producto 1",1);

                model.addSeries(java);

                return model;

        }



}