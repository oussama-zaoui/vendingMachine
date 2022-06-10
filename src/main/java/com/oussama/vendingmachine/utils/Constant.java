package com.oussama.vendingmachine.utils;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    public static final int OK=200;
    public static final int NOT_FOUND=404;
    public static final int FORBIDDEN=403;
    public static final int BAD_REQUEST=400;

    public static final ArrayList<Double> allowedAmount=new ArrayList<>(List.of(5.0,10.0,20.0,50.0,100.0));
}
