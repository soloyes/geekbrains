package csi;

import java.util.*;

public class PriceJoiner {

    public List<Price> join(List<Price> oldPrices, List<Price> newPrices) throws CloneNotSupportedException {

        List<Price> result = new LinkedList<>();

        //
        Map<Integer, LinkedList<Price>> oldPricesByDepartments = new TreeMap<>();
        Map<Integer, LinkedList<Price>> newPricesByDepartments = new TreeMap<>();
        separateByDepartment(oldPrices, oldPricesByDepartments);
        separateByDepartment(newPrices, newPricesByDepartments);
        //

        for (Map.Entry<Integer, LinkedList<Price>> e : newPricesByDepartments.entrySet()) {
            if (oldPricesByDepartments.get(e.getKey()) != null)
                while (e.getValue().size() != 0) {
                    LinkedList<Price> newPricesByIDAndNumber =
                            separateByIDAndNumber(e.getValue(), e.getValue().getFirst());
                    LinkedList<Price> oldPricesByIDAndNumber =
                            separateByIDAndNumber(oldPricesByDepartments.get(e.getKey()), newPricesByIDAndNumber.getFirst());
                    //Add new prices for whole new ID and Number
                    if (oldPricesByIDAndNumber.size() == 0)
                        result.addAll(newPricesByIDAndNumber);
                    else {
                        //Join dates
                        TreeSet<Date> allDates = new TreeSet<>();
                        makeDatesSet(newPricesByIDAndNumber, oldPricesByIDAndNumber, allDates);
                        ArrayList<Date> allDatesList = new ArrayList<>(allDates);
                        //
                        //Restore Prices from allDates collection
                        LinkedList<Price> consolidateList = new LinkedList<>();
                        //Here addAll order is important by algorithm
                        consolidateList.addAll(oldPricesByIDAndNumber);
                        consolidateList.addAll(newPricesByIDAndNumber);
                        //

                        for (int i = 0; i < allDatesList.size() - 1; i++) {
                            Price p;
                            if ((p = findBegin(consolidateList, allDatesList.get(i))) != null) {
                                Price pp = (Price) p.clone();
                                pp.setEnd(allDatesList.get(i + 1));
                                result.add(pp);
                            } else {
                                if ((p = findEnd(consolidateList, allDatesList.get(i + 1))) != null) {
                                    Price pp = (Price) p.clone();
                                    pp.setBegin(allDatesList.get(i));
                                    result.add(pp);
                                }
                            }
                        }
                        //
                    }
                }
            //Add new prices for whole new department
            result.addAll(e.getValue());
        }
        return result;
    }

    private Price findBegin(LinkedList<Price> linkedList, Date date) {
        Iterator<Price> iterator = linkedList.descendingIterator();
        while (iterator.hasNext()) {
            Price p = iterator.next();
            if (p.getBegin().equals(date)) {
                return p;
            }
        }
        return null;
    }

    private Price findEnd(LinkedList<Price> linkedList, Date date) {
        for (Price p : linkedList) {
            if (p.getEnd().equals(date)) {
                return p;
            }
        }
        return null;
    }

    private void makeDatesSet(LinkedList<Price> newPricesByIDAndNumber, LinkedList<Price> oldPricesByIDAndNumber, TreeSet<Date> allDates) {
        TreeSet<Date> excluded = new TreeSet<>();
        for (Price np : newPricesByIDAndNumber) {
            for (Price op : oldPricesByIDAndNumber) {
                if (op.getBegin().before(np.getBegin()) && !excluded.contains(op.getBegin())) {
                    allDates.add(op.getBegin());
                } else excluded.add(op.getBegin());
                if (op.getEnd().after(np.getEnd()) && !excluded.contains(op.getEnd())) {
                    allDates.add(op.getEnd());
                } else excluded.add(op.getEnd());
                allDates.add(np.getEnd());
                allDates.add(np.getBegin());
            }
        }
        excluded = null;
    }

    private LinkedList<Price> separateByIDAndNumber(LinkedList<Price> list, Price price) {
        LinkedList<Price> result = new LinkedList<>();
        Iterator<Price> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Price p = iterator.next();
            if (price.getProduct_code().equals(p.getProduct_code()) &&
                    price.getNumber() == p.getNumber()) {
                result.add(p);
                iterator.remove();
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