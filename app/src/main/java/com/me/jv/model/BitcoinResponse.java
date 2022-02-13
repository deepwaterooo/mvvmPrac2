package com.me.jv.model;

public class BitcoinResponse {
    TimeItem time;
    String disclaimer;
    String chartName;
    BPI bpi;
    public BitcoinResponse() {
    }
    public TimeItem getTimeItem() {
        return time;
    }
    public void setTimeItme(TimeItem time) {
        this.time = time;
    }
    public String getDisclaimer() {
        return disclaimer;
    }
    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }
    public String getChartName() {
        return chartName;
    }
    public void setChartName(String chartName) {
        this.chartName = chartName;
    }
    public BPI getBpi() {
        return bpi;
    }
    public void setBpi(BPI bpi) {
        this.bpi = bpi;
    }
    // @Override
    public String toString() {
        String ans = time.toString();
        ans += "\ndisclaimer: " + disclaimer + "\n" + bpi.toString();
        return ans;
    }
    public static class BPI {
        Currency USD;
        Currency GBP;
        Currency EUR;
        public BPI() {
            USD = new Currency();
            GBP = new Currency();
            EUR = new Currency();
        }
        public Currency getUSD() {
            return USD;
        }
        public void setUSD(Currency USD) {
            this.USD = USD;
        }
        public Currency getGBP() {
            return GBP;
        }
        public void setGBP(Currency iso) {
            this.GBP = iso;
        }
        public Currency getEUR() {
            return EUR;
        }
        public void setEUR(Currency duk) {
            this.EUR = duk;
        }
        // @Override
        public String toString() {
            return "updated: " + USD.toString() + "\nupdatedISO:" + GBP.toString() + "\nupdatedduk: " + EUR.toString();
        }
        public static class Currency {
            String code;
            String symbol; // 暂时这个货币符号还转得不对
            String rate;
            String description;
            double rate_float;
            public String getRate() {
                return rate;
            }
            public void setRate(String rate) {
                this.rate = rate;
            }
            public String getCode() {
                return code;
            }
            public void setCode(String code) {
                this.code = code;
            }
            public String getSymbol() {
                return symbol;
            }
            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }
            public String getDescription() {
                return description;
            }
            public void setDescription(String description) {
                this.description = description;
            }
            public double getRate_float() {
                return rate_float;
            }
            public void setRate_float(double rate_float) {
                this.rate_float = rate_float;
            }
            // @Override
            public String toString() {
                return "code: " + code + "\nsymbol: " + symbol + "\nrate: " + rate + "\ndis: " + description + "\nrate_float: " + rate_float;
            }
        }
    }
    public static class TimeItem {
        String updated;
        String updatedISO;
        String updateduk;
        public String getUpdated() {
            return updated;
        }
        public void setUpdated(String updated) {
            this.updated = updated;
        }
        public String getUpdatedISO() {
            return updatedISO;
        }
        public void setUpdatedISO(String updatedISO) {
            this.updatedISO = updatedISO;
        }
        public String getUpdateduk() {
            return updateduk;
        }
        public void setUpdateduk(String updateduk) {
            this.updateduk = updateduk;
        }
        // public TimeItem() {
        // }
        // @Override
        public String toString() {
            return "updated: " + updated + "\nupdatedISO:" + updatedISO + "\nupdatedduk: " + updateduk;
        }
    }
}
