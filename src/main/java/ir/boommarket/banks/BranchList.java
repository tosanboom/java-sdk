package ir.boommarket.banks;

import java.util.ArrayList;
import java.util.List;

/**
 * As its name suggests, this dumb data class will encapsulates list of branches for a given
 * bank
 *
 * @author Ali Dehghani
 */
public class BranchList {
    private List<Branch> branches = new ArrayList<>();

    /**
     * @return list of branches
     */
    public List<Branch> branches() {
        return branches;
    }

    /**
     * Encapsulates information about each branch
     */
    public static class Branch {
        private String code;
        private String name;
        private String address;
        private String city;

        /**
         * @return The branch code
         */
        public String code() {
            return code;
        }

        /**
         * @return Name of the branch
         */
        public String name() {
            return name;
        }

        /**
         * @return Address of the branch
         */
        public String address() {
            return address;
        }

        /**
         * @return The branch city
         */
        public String city() {
            return city;
        }

        @Override
        public String toString() {
            return "Branch{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BranchList{" +
                "branches=" + branches +
                '}';
    }
}