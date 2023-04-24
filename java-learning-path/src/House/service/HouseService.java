package House.service;

import House.domain.House;

public class HouseService {
    private House[] houses;
    private int count = 1;
    private int idCounter = 1;

    public HouseService(int size){
        houses = new House[size];
        houses[0] = new House(1,"jack","112","海淀区",2000,"未出租");
    }
    public House[] list(){
        return houses;
    }
    public boolean add(House newHouse){
        if (count >= houses.length){
            System.out.println("数组已满，不能继续添加");
            return false;
        }
        houses[count++] = newHouse;
        newHouse.setId(++idCounter);
        return true;
    }

    public boolean delete(int houseID){
        int index = -1;
        for (int i=0; i<count; i++){
            if (houseID == houses[i].getId()){
                index = i;
            }
        }
        if (index == -1){
            return false;
        }
        for(int i=index; i<count-1; i++){
            houses[i] = houses[i+1];
        }
        houses[--count] = null;
        return true;
    }

    public House findID(int houseID){
        for (int i=0; i<count; i++){
            if (houseID == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }
}
