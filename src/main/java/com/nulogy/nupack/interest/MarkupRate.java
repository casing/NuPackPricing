/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack.interest;

import com.nulogy.nupack.MaterialType;

/**
 *
 * @author casing
 */
public interface MarkupRate {
    double getMaterialMarkupRate(MaterialType type);
    double getFlatMarkupRate();
    double getWorkerMarkupRate();
}
