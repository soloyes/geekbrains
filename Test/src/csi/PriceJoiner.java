package csi;

import java.util.*;

public class PriceJoiner {

    public List<Price> join(List<Price> oldPrices, List<Price> newPrices) throws CloneNotSupportedException {

        LinkedList<Price> result = new LinkedList<>();

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
        return merge(result);
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
        Iterator<Price> iterator = linkedList.descendingIterator();
        while (iterator.hasNext()) {
            Price p = iterator.next();
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
                if (!excluded.contains(op.getBegin())) {
                    if (op.getBegin().after(np.getBegin()) && op.getBegin().before(np.getEnd())) {
                        excluded.add(op.getBegin());
                    }
                }

                if (!excluded.contains(op.getEnd())) {
                    if (op.getEnd().after(np.getBegin()) && op.getEnd().before(np.getEnd())) {
                        excluded.add(op.getEnd());
                    }
                }
                allDates.add(op.getBegin());
                allDates.add(op.getEnd());
            }
            allDates.add(np.getEnd());
            allDates.add(np.getBegin());
        }

        for (Date ex : excluded) {
            allDates.remove(ex);
        }
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

    private LinkedList<Price> merge(LinkedList<Price> linkedList) {
        LinkedList<Price> result = new LinkedList<>();
        for (int i = 0; i < linkedList.size() - 1; i++) {
            if (linkedList.get(i).getValue() == linkedList.get(i + 1).getValue() &&
                    linkedList.get(i).getDepart() == linkedList.get(i + 1).getDepart() &&
                    linkedList.get(i).getNumber() == linkedList.get(i + 1).getNumber()) {
                linkedList.get(i).setEnd(linkedList.get(i + 1).getEnd());
                result.add(linkedList.get(i));
                i++;
            } else
                result.add(linkedList.get(i));
        }

        if (linkedList.getLast().getValue() == result.getLast().getValue() &&
                linkedList.getLast().getDepart() == result.getLast().getDepart() &&
                linkedList.getLast().getNumber() == result.getLast().getNumber()) {
            result.getLast().setEnd(linkedList.getLast().getEnd());
        } else result.add(linkedList.getLast());

        return result;
    }
}