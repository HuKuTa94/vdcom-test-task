package part1.task3;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class GroupCollection
{
    @Getter
    private boolean hasUnknownGroup = false;
    private RatioGroup unknownGroup;
    private List<RatioGroup> knownGroups = new ArrayList<>();


    public boolean put( RatioGroup newRatioGroup)
    {
        if( hasUnknownGroup )
            return false;

        if( knownGroups.size() == 0 ) {
            addGroup( newRatioGroup );
            return true;
        }

        for( RatioGroup ratioGroup : knownGroups ) {
            if( haveRelationship( ratioGroup, newRatioGroup )) {
                addGroup( newRatioGroup );
                return true;
            }
        }

        return false;
    }

    private boolean haveRelationship( RatioGroup g1, RatioGroup g2 ) {
        String g1Left = g1.getLeftValueName();
        String g2Left = g2.getLeftValueName();
        String g1Right = g1.getRightValueName();
        String g2Right = g2.getRightValueName();

        return ( g1Left.equals( g2Left) || g1Left.equals( g2Right )) ||
               ( g1Right.equals( g2Left ) || g1Right.equals( g2Right ));
    }

    private void addGroup( RatioGroup ratioGroup ) {
        if( ratioGroup.hasUnknownValue() ) {
            unknownGroup = ratioGroup;
            hasUnknownGroup = true;
        }
        else {
            knownGroups.add( ratioGroup );
        }
    }

    private Value getNormalizedNumberByValueName( String valueName ) {
        for( RatioGroup ratioGroup : knownGroups) {
            Ratio ratio = ratioGroup.getUnitByLeftValue();
            if( ratio.getLeft().getName().equals( valueName )) {
                return ratio.getRight();
            }

            ratio = ratioGroup.getUnitByRightValue();
            if( ratio.getRight().getName().equals( valueName )) {
                return ratio.getLeft();
            }
        }
        return null;
    }

    public String calculate() {
        if( !hasUnknownGroup ) {
            return null;
        }

        Value knownValue = unknownGroup.getRatio().getKnownValue();
        Value knownNormalizedValue = getNormalizedNumberByValueName( knownValue.getName() );

        Value unknownValue = unknownGroup.getRatio().getUnknownValue().get();
        Value unknownNormalizedValue = getNormalizedNumberByValueName( unknownValue.getName() );

       if( unknownNormalizedValue == null )
            return knownValue + " = ? " + unknownValue.getName() + " - Преобразование невозможно";

        double result;
        assert knownNormalizedValue != null;
        result = Math.max( unknownNormalizedValue.getNumber(), knownNormalizedValue.getNumber() ) /
                 Math.min( unknownNormalizedValue.getNumber(), knownNormalizedValue.getNumber() );

        result *= unknownGroup.getRatio().getKnownValue().getNumber();
        unknownValue.setNumber( result );

        return knownValue.toString() + " = " + unknownValue.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for( RatioGroup ratioGroup : knownGroups ) {
            builder.append( ratioGroup.toString() )
                   .append( "\n");
        }
        return builder.toString();
    }
}
