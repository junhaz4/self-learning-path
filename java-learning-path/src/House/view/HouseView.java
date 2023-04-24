package House.view;

import House.domain.House;
import House.service.HouseService;
import House.utils.Utility;

public class HouseView {
    private boolean loop = true;
    private char key = ' ';
    private HouseService houseService = new HouseService(10);
    public void listHouses(){
        System.out.println("\n============房屋列表============");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();
        for (int i=0; i<houses.length; i++){
            if (houses[i] == null){
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("============房屋列表展示完毕============");
    }

    public void addHouse(){
        System.out.println("===========添加房屋============");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);
        House house = new House(0, name, phone, address, rent, state);
        if (houseService.add(house)){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    public void deleteHouse(){
        System.out.println("===========删除房屋============");
        System.out.print("请输入待删除房屋编号：");
        int houseId = Utility.readInt();
        if (houseId == -1){
            System.out.println("放弃删除房屋信息");
        }
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y'){
            if (houseService.delete(houseId)){
                System.out.println("删除信息成功");
            }else{
                System.out.println("房屋信息不存在");
            }
        }else{
            System.out.println("放弃删除");
        }
    }
    public void exit(){
        char c = Utility.readConfirmSelection();
        if (c == 'Y'){
            loop = false;
        }
    }

    public void findHouse(){
        System.out.println("===========查找房屋============");
        System.out.print("请输入查找房屋id：");
        int houseId = Utility.readInt();
        House house = houseService.findID(houseId);
        if (house != null){
            System.out.println(house);
        }else {
            System.out.println("房子不存在");
        }
    }

    public void updateHouse(){
        System.out.println("===========修改房屋============");
        System.out.print("请输入房屋id：");
        int houseId = Utility.readInt();
        House house = houseService.findID(houseId);
        if (house == null){
            System.out.println("房子不存在");
            return;
        }
        System.out.println(house.getName());
        String name = Utility.readString(8,"");
        if (!"".equals(name)){
            house.setName(name);
        }
        System.out.println(house.getPhone());
        String phone = Utility.readString(12,"");
        if (!"".equals(phone)){
            house.setPhone(name);
        }
        System.out.println(house.getAddress());
        String address = Utility.readString(18,"");
        if (!"".equals(address)){
            house.setAddress(name);
        }
    }

    public void mainMenu(){
        do{
            System.out.println("============房屋出租系统============");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删除房屋信息");
            System.out.println("\t\t\t4 修改房屋信息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退      出");
            System.out.println("请输入选择");
            key = Utility.readChar();
            switch (key){
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    deleteHouse();
                    break;
                case '4':
                    System.out.println("修改");
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
            }
        }while(loop);
    }

}
