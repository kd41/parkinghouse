package ee.home.parkinghouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ee.home.parkinghouse.util.JsonDateDeserializer;
import ee.home.parkinghouse.util.JsonDateSerializer;

public class Fee {

    @JsonIgnore
    private long id;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date start;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date end;
    private List<Part> parts = new ArrayList<>();
    private long totalCost;
    private String currency;
    private User user;
    @JsonIgnore
    private boolean payed;
    private boolean invoiced;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fee other = (Fee) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Fee [id=");
        builder.append(id);
        builder.append(", start=");
        builder.append(start);
        builder.append(", end=");
        builder.append(end);
        builder.append(", parts=");
        builder.append(parts);
        builder.append(", totalCost=");
        builder.append(totalCost);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", user=");
        builder.append(user);
        builder.append(", payed=");
        builder.append(payed);
        builder.append(", invoiced=");
        builder.append(invoiced);
        builder.append("]");
        return builder.toString();
    }

    public static class Part {
        @JsonSerialize(using = JsonDateSerializer.class)
        @JsonDeserialize(using = JsonDateDeserializer.class)
        private Date start;
        @JsonSerialize(using = JsonDateSerializer.class)
        @JsonDeserialize(using = JsonDateDeserializer.class)
        private Date end;
        private int count;
        private long cost;
        private long totalCost;

        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public long getCost() {
            return cost;
        }

        public void setCost(long cost) {
            this.cost = cost;
        }

        public long getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(long totalCost) {
            this.totalCost = totalCost;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Part [start=");
            builder.append(start);
            builder.append(", end=");
            builder.append(end);
            builder.append(", count=");
            builder.append(count);
            builder.append(", cost=");
            builder.append(cost);
            builder.append(", totalCost=");
            builder.append(totalCost);
            builder.append("]");
            return builder.toString();
        }

    }

}
