class RobberProblem {
    
    private int[] nums;
    private Map<Integer, Integer> map;
    
    public int rob(int[] nums) {
       return TopDown(nums);
    }
    
    private int generic (int[] nums) {
        int robEvenSum = 0;
        int robOddSum = 0;
        for (int i = 0; i < nums.length; i++) {
            robEvenSum += (i%2 == 0) ? nums[i] : 0;
            robOddSum += (i%2 != 0) ? nums[i] : 0;
        }
        return robEvenSum > robOddSum ? robEvenSum : robOddSum;
    }
    
    private int BottomUp (int[] nums) {
        
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        
        
        //initialise new array
        int[] result = new int[nums.length+1];
         
        //Base case definations
        result[0] = nums[0];
        result[1] = Math.max(nums[0] , nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            result[i] = Math.max(result[i - 1], result[i - 2]+nums[i]);
        } 
        
        return result[nums.length -1];
    }
    
    private int calculateIth (int i) {
        if(i == 0) return nums[0];
        if(i == 1) return Math.max(nums[0], nums[1]);
        
        if (map.get(i) == null) {
            map.put(i, Math.max(calculateIth(i - 1), calculateIth(i - 2)+nums[i]));
        }
        
        return map.get(i);
    }
    
    private int TopDown (int[] nums) {
        this.nums = nums;
        this.map = new HashMap<Integer, Integer>();
        return calculateIth(nums.length -1);

    }
}
