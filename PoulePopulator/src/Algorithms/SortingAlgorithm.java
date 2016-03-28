/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import FileChoosing.csvFileHandler.CsvFileMetaData;

/**
 * @author Kraaijeveld
 */

public class SortingAlgorithm 
{
    private CsvFileMetaData metadata;
    
    public SortingAlgorithm(CsvFileMetaData metaData)
    {
        metadata = metaData;
        System.out.print("Got the metadata, thanks!");
    }
}
