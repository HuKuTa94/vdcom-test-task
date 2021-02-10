package part1.task3;

import lombok.Getter;

@Getter
public class RatioGroup
{
    private Ratio ratio;
    private Ratio unitByLeftValue;
    private Ratio unitByRightValue;

    public RatioGroup(  Ratio ratio ) {
        this.ratio = ratio;
        normalize();
    }

    private void normalize() {
        Value right = new Value(
                ratio.getRight().getNumber() / ratio.getLeft().getNumber(),
                ratio.getRight().getName() );

        Value left = new Value(
                ratio.getLeft().getNumber() / ratio.getRight().getNumber(),
                ratio.getLeft().getName() );

        unitByLeftValue = new Ratio(
                                new Value( 1.0, ratio.getLeft().getName() ),
                                right );

        unitByRightValue = new Ratio(
                                left,
                                new Value( 1.0, ratio.getRight().getName() ));
    }

    public String getLeftValueName() {
        return ratio.getLeft().getName();
    }

    public String getRightValueName() {
        return ratio.getRight().getName();
    }

    public boolean hasUnknownValue() {
        return ratio.hasUnknownValue();
    }

    @Override
    public String toString() {
        return "default proportion: " + ratio + "\nunit proportions:\n\t" +
                unitByLeftValue + "\n\t" + unitByRightValue;
    }
}
