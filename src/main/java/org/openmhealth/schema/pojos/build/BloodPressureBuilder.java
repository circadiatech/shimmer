package org.openmhealth.schema.pojos.build;

import org.joda.time.DateTime;
import org.openmhealth.schema.pojos.*;
import org.openmhealth.schema.pojos.generic.DescriptiveStatistic;
import org.openmhealth.schema.pojos.generic.TimeFrame;

import java.math.BigDecimal;

public class BloodPressureBuilder implements SchemaPojoBuilder<BloodPressure> {

    private BloodPressure bloodPressure;

    public BloodPressureBuilder() {
        bloodPressure = new BloodPressure();
        bloodPressure.setEffectiveTimeFrame(new TimeFrame());
    }

    public BloodPressureBuilder setValues(BigDecimal systolic, BigDecimal diastolic) {
        SystolicBloodPressure systolicBloodPressure = new SystolicBloodPressure();
        systolicBloodPressure.setValue(systolic);
        systolicBloodPressure.setUnit(BloodPressureUnit.mmHg);
        DiastolicBloodPressure diastolicBloodPressure = new DiastolicBloodPressure();
        diastolicBloodPressure.setValue(diastolic);
        diastolicBloodPressure.setUnit(BloodPressureUnit.mmHg);
        bloodPressure.setDiastolic(diastolicBloodPressure);
        bloodPressure.setSystolic(systolicBloodPressure);
        return this;
    }

    public BloodPressureBuilder setPositionDuringMeasurement(
        PositionDuringMeasurement positionDuringMeasurement) {

        bloodPressure.setPositionDuringMeasurement(positionDuringMeasurement);
        return this;
    }

    public BloodPressureBuilder setDescriptiveStatistic(DescriptiveStatistic descriptiveStatistic) {
        bloodPressure.setDescriptiveStatistic(descriptiveStatistic);
        return this;
    }

    public BloodPressureBuilder setNotes(String notes) {
        bloodPressure.setNotes(notes);
        return this;
    }

    @Override
    public BloodPressure build() {
        return bloodPressure;
    }

    public BloodPressureBuilder setTimeTaken(DateTime dateTime) {
        bloodPressure.setEffectiveTimeFrame(TimeFrame.withDateTime(dateTime));
        return this;
    }
}
