package csi;

import com.sun.xml.internal.messaging.saaj.util.Base64;

import java.util.*;

public class PriceJoiner {

    public List<Price> join(List<Price> oldPrices, List<Price> newPrices) {

        List<Price> result = new LinkedList<>();

        //
        Map<Integer, LinkedList<Price>> oldPricesByDepartments = new TreeMap<>();
        Map<Integer, LinkedList<Price>> newPricesByDepartments = new TreeMap<>();
        separateByDepartment(oldPrices, oldPricesByDepartments);
        separateByDepartment(newPrices, newPricesByDepartments);
        //

        for (Map.Entry<Integer, LinkedList<Price>> e : newPricesByDepartments.entrySet()) {
            if (oldPricesByDepartments.get(e.getKey()) == null)
                //Add new prices for whole new department
                result.addAll(e.getValue());
            else {
                //
                while (e.getValue().size() != 0) {
                    LinkedList<Price> newPricesByIDAndNumber =
                            separateByIDAndNumber(e.getValue(), e.getValue().getFirst());
                    //System.out.println(newPricesByIDAndNumber);
                    LinkedList<Price> oldPricesByIDAndNumber =
                            separateByIDAndNumber(oldPricesByDepartments.get(e.getKey()), newPricesByIDAndNumber.getFirst());
                    //Add new prices for whole new ID and Number
                    System.out.println(oldPricesByIDAndNumber);
                    if (oldPricesByIDAndNumber.size() == 0)
                        result.addAll(newPricesByIDAndNumber);
                    else {
                        System.out.println("BLA");
                    }
                }
                //
            }
        }

        System.out.println(result);
        return null;
    }

    private LinkedList<Price> separateByIDAndNumber(LinkedList<Price> list, Price price) {
        LinkedList<Price> result = new LinkedList<>();
        for (Price p : list) {
            if (price.getProduct_code().equals(p.getProduct_code()) &&
                    price.getNumber() == p.getNumber()) {
                result.add(p);
                list.remove(p);
            }
        }
        return result;
    }

    private void separateByDepartment(List<Price> prices, Map<Integer, LinkedList<Price>> map) {
        for (Price p : prices) {
            if (map.get(p.getDepart()) == null) {
                LinkedList<Price> list = new LinkedList<>();
                list.add(p);
                map.put(p.getDepart(), list);
            } else map.get(p.getDepart()).add(p);
        }
    }
}

