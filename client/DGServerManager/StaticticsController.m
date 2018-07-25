//
//  StaticticsController.m
//  DGServerManager
//
//  Created by apple on 2018/3/26.
//  Copyright © 2018年 apple. All rights reserved.
//

#import "StaticticsController.h"
#import "ServerManager.h"

@interface StaticticsController ()<ASFTableViewDelegate>

@property (nonatomic, retain) NSMutableArray *rowsArray;
@property (nonatomic, strong) NSString *gameCode; //模型数据集
@property (nonatomic, strong) NSDate *currentDate; //模型数据集

@end

@implementation StaticticsController

- (id)initWithCoder:(NSCoder*)aDecoder
{
    if(self = [super initWithCoder:aDecoder])
    {
        _rowsArray = [[NSMutableArray alloc] init];
    }
    return self;
}

- (void)initWithCode:(NSString*)code
{
    self.gameCode = code;
    NSDate *datenow = [NSDate date];
    self.currentDate = datenow;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    NSArray *cols = @[@"游戏名",@"icon展示",@"icon点击",@"激活"];
    NSArray *weights = @[@(0.3f),@(0.25f),@(0.25f),@(0.2f)];
    NSDictionary *options = @{kASF_OPTION_CELL_TEXT_FONT_SIZE : @(16),
                              kASF_OPTION_CELL_TEXT_FONT_BOLD : @(true),
                              kASF_OPTION_CELL_BORDER_COLOR : [UIColor lightGrayColor],
                              kASF_OPTION_CELL_BORDER_SIZE : @(2.0),
                              kASF_OPTION_BACKGROUND : [UIColor colorWithRed:239/255.0 green:244/255.0 blue:254/255.0 alpha:1.0]};
    
    [_mASFTableView setDelegate:self];
    [_mASFTableView setBounces:NO];
    [_mASFTableView setSelectionColor:[UIColor colorWithRed:242/255.0 green:242/255.0 blue:242/255.0 alpha:1.0f]];
    [_mASFTableView setTitles:cols
                  WithWeights:weights
                  WithOptions:options
                    WitHeight:32 Floating:YES];
    
    [self refreseTableView];
    
    //3、给容器containerView绑定左右滑动清扫手势
    UISwipeGestureRecognizer *leftSwipe = [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(swipe:)];
    leftSwipe.direction = UISwipeGestureRecognizerDirectionLeft; //设置向左清扫
    [_mASFTableView addGestureRecognizer:leftSwipe];
    
    UISwipeGestureRecognizer *rightSwipe = [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(swipe:)];
    rightSwipe.direction = UISwipeGestureRecognizerDirectionRight;//设置向右清扫
    [_mASFTableView addGestureRecognizer:rightSwipe];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)refreseTableView
{
    [_rowsArray removeAllObjects];
    
    NSMutableDictionary *pushStatictics=[ServerManager shareServer].pushStatictics;
    NSArray *pushArray = pushStatictics[[NSNumber numberWithInteger:self.gameCode.integerValue]];
    
    int nCountShow = 0;
    int nCountClick = 0;
    int nCountActive = 0;
    for (int i=0; i<pushArray.count; i++) {
        id value = pushArray[i];
        NSString*stringShow = value[@"iconShow"];
        int show =[stringShow intValue];
        nCountShow+=show;
        NSString*stringClick = value[@"iconClick"];
        int click =[stringClick intValue];
        nCountClick+=click;
        NSString*stringActive = value[@"iconActive"];
        int active =[stringActive intValue];
        nCountActive+=active;
    }
    for (int i=0; i<pushArray.count; i++) {
        id value = pushArray[i];
        NSString *pushGameID = value[@"pushGameId"];
        GameInfo *info = [ServerManager shareServer].dataDoc[pushGameID];
        NSString*stringShow = value[@"iconShow"];
        int show =[stringShow intValue];
        NSString*stringClick = value[@"iconClick"];
        int click =[stringClick intValue];
        NSString*stringActive = value[@"iconActive"];
        int active =[stringActive intValue];
        [_rowsArray addObject:@{
                                kASF_ROW_ID :
                                    @(i),
                                
                                kASF_ROW_CELLS :
                                    @[@{kASF_CELL_TITLE : info.name, kASF_OPTION_CELL_TEXT_ALIGNMENT : @(NSTextAlignmentCenter)},
                                      @{kASF_CELL_TITLE : [NSString stringWithFormat:@"%d(%.1f%%)",show,show*100.0/nCountShow ], kASF_OPTION_CELL_TEXT_ALIGNMENT : @(NSTextAlignmentCenter)},
                                      @{kASF_CELL_TITLE :  [NSString stringWithFormat:@"%d(%.1f%%)",click,click*100.0/nCountClick ], kASF_OPTION_CELL_TEXT_ALIGNMENT : @(NSTextAlignmentCenter)},
                                      @{kASF_CELL_TITLE :  [NSString stringWithFormat:@"%d(%.1f%%)", active,active*100.0/nCountActive], kASF_OPTION_CELL_TEXT_ALIGNMENT : @(NSTextAlignmentCenter)}],
                                
                                kASF_ROW_OPTIONS :
                                    @{kASF_OPTION_BACKGROUND : [UIColor whiteColor],
                                      kASF_OPTION_CELL_PADDING : @(5),
                                      kASF_OPTION_CELL_BORDER_COLOR : [UIColor lightGrayColor]},
                                
                                @"some_other_data" : @(123)}];
    }
    
    
    [_mASFTableView setRows:_rowsArray];
}

- (void)pullStaticticsData:(int) Interval
{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"YYYYMMdd"];
    self.currentDate = [NSDate dateWithTimeInterval:24*60*60*Interval sinceDate:self.currentDate];//前一天
    NSString *currentTimeString = [formatter stringFromDate:self.currentDate];
    [[ServerManager shareServer] startGetStatictics:currentTimeString back:^(BOOL success) {
        [self refreseTableView];
    }];
}

//左滑动和右滑动手势
- (void)swipe: (UISwipeGestureRecognizer *)sender
{
    if (sender.direction == UISwipeGestureRecognizerDirectionLeft){
        [self pullStaticticsData:1];
    }
    else if (sender.direction == UISwipeGestureRecognizerDirectionRight){
        [self pullStaticticsData:-1];
    }
}

#pragma mark - ASFTableViewDelegate
- (void)ASFTableView:(ASFTableView *)tableView DidSelectRow:(NSDictionary *)rowDict WithRowIndex:(NSUInteger)rowIndex {
    NSLog(@"%lu", rowIndex);
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
