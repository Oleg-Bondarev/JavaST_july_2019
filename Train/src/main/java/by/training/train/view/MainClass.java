package by.training.train.view;

import by.training.train.controller.Controller;
import by.training.train.dao.PassengerTrainRepository;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.TrainService;

public final class MainClass {
    private MainClass() { }
    /**
     * @param args -
     * */
    public static void main(final String[] args) {
        System.out.println("Start loading.");

        Controller controller = new Controller();
        /*System.out.println(controller.executeeRequest(
"READ_CARRIAGES;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\input_inform.txt"));
        System.out.println(controller.executeeRequest(
    "SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt"));*/
        //System.out.println(controller.executeeRequest("ALL_BAGGAGE;"));
        //System.out.println(controller.executeeRequest("All_Passengers;"));
        //
        /*System.out.println(controller.
        executeeRequest("Add_carriage;Seating,2,22,buisiness,4.5,false,true"));*/
        //System.out.println(controller.executeeRequest(
    //"SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt"));
        //
        //String wrongRequest = null;
        //System.out.println(controller.executeeRequest(wrongRequest));
        //remove
        //System.out.println(controller.executeeRequest("delete_carriage;3"));
        /*System.out.println(controller.executeeRequest(
"SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt"));*/
        //find by pass range +
        //System.out.println(controller.
        //executeeRequest("find_by_passenger_range;10;50;"
        //    + "E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt"));
        //find by pass range -
        //System.out.println(controller.
        //executeeRequest("find_by_passenger_range;109;502;"
        //    + "E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt"));
        //sort by baggage
        //System.out.println(controller.executeeRequest("Sort_by_baggage;"));
        //controller.executeeRequest("SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt");
        //sort by passengers
        //System.out.println(controller.executeeRequest("Sort_by_count_passengers;"));
        //controller.executeeRequest("SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt");
        //sort by passengers than baggage
        //controller.executeeRequest("Add_carriage;Seating,2,22,buisiness,3.5,false,true");
        //controller.executeeRequest("sort_by_passengers_then_by_baggage;");
        //controller.executeeRequest("SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt");

       System.out.println(controller.executeeRequest(
                "READ_CARRIAGES;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\file_with_wrong_inf.txt"));
        System.out.println(controller.executeeRequest(
                "SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt"));

        /*System.out.println(controller.executeeRequest(
                "READ_CARRIAGES;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\input_inform.txt"));
        System.out.println(controller.executeeRequest(
                "SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt"));
        System.out.println(controller.executeeRequest("ALL_BAGGAGE;"));
        System.out.println(controller.executeeRequest("All_Passengers;"));
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        TrainService trainService = serviceFactory.getTrainService();
        PassengerTrainRepository observer = PassengerTrainRepository.getInstance();
        PassengerCarriage passengerCarriage;*/
        //passengerCarriage.addObserver(observer);
        //trainService.getTrain().get(1).setCountOfPassengers(25);
        //trainService.getTrain().get(1).setBagageOnPassenger(5.8);
        /*int a = trainService.getTotalPassengers();
        double b = trainService.getTotalBaggage();
        System.out.println("Pass= " + a);
        System.out.println("Bagg= " + b);
        System.out.println(controller.executeeRequest(
                "SHOW_TRAIN;E:\\JavaWeb\\JavaST_july_2019\\Train\\data\\report_file.txt"));
        System.out.println(controller.executeeRequest("ALL_BAGGAGE;"));
        System.out.println(controller.executeeRequest("All_Passengers;"));*/

    }
}
