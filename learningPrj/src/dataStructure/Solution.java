package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {

    static List<String> paginate(int resultsPerPage, List<String> results) {
        if (resultsPerPage <= 0) {
            return Collections.emptyList();
        }
        if ((results == null) || (results.isEmpty())) {
            return Collections.emptyList();
        }

        List<String> resultList = new LinkedList<String>();
        Set<String> hostIdSet = new HashSet<String>();
        int index = 0;
        int count = 0;
        while (!results.isEmpty()) {
            if (count >= resultsPerPage) {
                index = 0;
                count = 0;
                hostIdSet.clear();
                resultList.add("");
            }

            String eachData = results.get(index);
            if (!eachData.contains(",")) {
                results.remove(index);
                continue;
            }
            String[] eachDatas = eachData.split(",");
            if (eachDatas.length != 4) {
                // illegal data
                results.remove(index);
                continue;
            }
            String eachHostId = eachDatas[0];
            if (hostIdSet.contains(eachHostId)) {
                index++;
                continue;
            }

            hostIdSet.add(eachHostId);
            resultList.add(results.remove(index));
            count++;
        }

        return resultList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int resultsPerPage = Integer.parseInt(bufferedReader.readLine().trim());

        int resultsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> results = new ArrayList<>();

        for (int i = 0; i < resultsCount; i++) {
            String resultsItem = bufferedReader.readLine();
            results.add(resultsItem);
        }

        List<String> res = paginate(resultsPerPage, results);

        for (int i = 0; i < res.size(); i++) {
            bufferedWriter.write(res.get(i));

            if (i != res.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
